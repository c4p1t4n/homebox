package school.sptech.server.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserUtil {

    public static String getToken() {
        String characters = "QWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890(){}[!*]|$#üáśẃéŕýíóǵḱĺḿńźãõẽĩũ+âôîêû";

        List<String> letters = Arrays.asList(characters.split(""));

        Collections.shuffle(letters);

        String token = letters.stream().reduce("", String::concat);

        return token.substring(0, 16);
    }

    // necessaria a verificação de se aquele token ja é o utilizado pelo user
    // (chances baixas mas nunca se sabe)
}
