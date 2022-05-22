package school.sptech.server.request;

public class RatingCreationRequest {
    private Integer rating;
    private String description;

    public RatingCreationRequest() {
    }

    public RatingCreationRequest(Integer rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
