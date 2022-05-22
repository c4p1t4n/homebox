package school.sptech.server.request;

public class AccomplishServiceInfo {
    private Double price;
    private String description;

    public AccomplishServiceInfo() {
    }

    public AccomplishServiceInfo(Double price, String description) {
        this.price = price;
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
