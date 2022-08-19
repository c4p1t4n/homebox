package school.sptech.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import school.sptech.server.model.Chat;
import school.sptech.server.model.User;
import school.sptech.server.model.UserHasChat;
import school.sptech.server.repository.ChatHasMsgRepository;
import school.sptech.server.repository.ChatRepository;
import school.sptech.server.repository.MsgRepository;
import school.sptech.server.repository.UserHasChatRepository;
import school.sptech.server.response.ChatsPerUser;
import school.sptech.server.service.UserService;

@SpringBootTest(classes = { ChatController.class })
public class ChatControllerTest {
    @Autowired
    private ChatController controller;
    @MockBean
    private ChatRepository dbRepositoryChat;
    @MockBean
    private UserHasChatRepository dbRepositoryUserHasChat;
    @MockBean
    private ChatHasMsgRepository dbRepositoryChatHasMsg;
    @MockBean
    private MsgRepository dbRepositoryMsg;
    @MockBean
    private UserService dbUserService;

    @Test
    void testGetChatsWhenListIsEmpty() {
        when(dbRepositoryChat.findAll()).thenReturn(new ArrayList<Chat>());
        ResponseEntity<List<Chat>> response = controller.getChats();

        assertEquals(204, response.getStatusCodeValue());

        assertNull(response.getBody());
    }

    @Test
    void testGetChatsWhenListIsNotEmpty() {

        Chat mock1 = mock(Chat.class);
        Chat mock2 = mock(Chat.class);
        List<Chat> listaMock = List.of(mock1, mock2);

        when(dbRepositoryChat.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Chat>> response = controller.getChats();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(listaMock, response.getBody());
    }

    @Test
    void testGetChatWhenTheIdIsInvalid() {

        when(dbRepositoryChat.existsById(1)).thenReturn(false);

        ResponseEntity<Chat> response = controller.getChat(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetChatWhenTheIdIsValid() {

        Chat mock1 = mock(Chat.class);

        when(dbRepositoryChat.existsById(1)).thenReturn(true);
        when(dbRepositoryChat.findById(1)).thenReturn(Optional.of(mock1));

        ResponseEntity<Chat> response = controller.getChat(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mock1, response.getBody());
    }

    @Test
    void testGetChatsPerUserWhenAllInfoIsFineAndListNotEmpty() {
        when(dbUserService.existsById(1)).thenReturn(true);
        UserHasChat mock1 = mock(UserHasChat.class);
        UserHasChat mock2 = mock(UserHasChat.class);
        User userMock = mock(User.class);
        userMock.setId(1);
        List<UserHasChat> listMock = List.of(mock1, mock2);

        when(dbUserService.findById(1)).thenReturn(Optional.of(userMock));
        when(dbRepositoryUserHasChat.findByUser(userMock)).thenReturn(listMock);

        ResponseEntity<List<ChatsPerUser>> response = controller.getChatsPerUser(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(listMock, response.getBody());

    }

    @Test
    void testGetChatsPerUserWhenAllInfoIsFineAndListIsEmpty() {
        when(dbUserService.existsById(1)).thenReturn(true);
        User userMock = mock(User.class);
        userMock.setId(1);

        when(dbUserService.findById(1)).thenReturn(Optional.of(userMock));
        when(dbRepositoryUserHasChat.findByUser(userMock)).thenReturn(new ArrayList<UserHasChat>());

        ResponseEntity<List<ChatsPerUser>> response = controller.getChatsPerUser(1);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());

    }

    @Test
    void testGetChatsPerUserWhenUserDoesNotExist() {
        when(dbUserService.existsById(1)).thenReturn(false);

        ResponseEntity<List<ChatsPerUser>> response = controller.getChatsPerUser(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

    }

    @Test
    void testPostChatWhenCustomerIdIsInvalid() {
        when(dbUserService.existsById(1)).thenReturn(false);

        ResponseEntity<Void> response = controller.postChat(1, 2);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testPostChatWhenWorkerIdIsInvalid() {

        when(dbUserService.existsById(1)).thenReturn(true);
        when(dbUserService.existsById(2)).thenReturn(false);

        ResponseEntity<Void> response = controller.postChat(1, 2);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

}
