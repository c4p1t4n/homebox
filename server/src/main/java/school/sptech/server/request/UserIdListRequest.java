package school.sptech.server.request;

import java.util.List;

public class UserIdListRequest {
    private List<Integer> userIds;

    public UserIdListRequest(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public UserIdListRequest() {
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

}
