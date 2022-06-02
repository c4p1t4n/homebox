package school.sptech.server.response;

import java.util.Objects;

import school.sptech.server.model.Category;
import school.sptech.server.model.User;

public class UserSearchQueryResult {
    private User user;
    private Double rating;
    private Double distance;
    private String category;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public UserSearchQueryResult(User user, Double rating, Double distance, String category) {
        this.user = user;
        this.rating = rating;
        this.distance = distance;
        this.category = category;
    }

    public UserSearchQueryResult(User user, Double rating, Double distance, Category category) {
        this.user = user;
        this.rating = rating;
        this.distance = distance;
        if (Objects.isNull(category)) {
            this.category = "";
        } else {
            this.category = category.getName();
        }
    }

    public UserSearchQueryResult(User user, Double rating, String category) {
        this.user = user;
        this.rating = rating;
        this.category = category;
    }

    public UserSearchQueryResult(User user, String category) {
        this.user = user;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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
