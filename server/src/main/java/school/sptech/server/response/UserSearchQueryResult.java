package school.sptech.server.response;

import school.sptech.server.model.User;

public class UserSearchQueryResult {
    private User user;
    private Double rating;

    public UserSearchQueryResult(User user, Double rating) {
        this.user = user;
        this.rating = rating;
    }

    public UserSearchQueryResult(User user) {
        this.user = user;
    }

    public UserSearchQueryResult() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
