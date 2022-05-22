package school.sptech.server.request;

public class ServiceCreationRequest {
    private Integer fkUser;
    private Integer fkCategory;
    private String name;
    private String description;
    private Double referencePrice;

    public ServiceCreationRequest() {
    }

    public ServiceCreationRequest(Integer fkUser, Integer fkCategory, String name, String description,
            Double referencePrice) {
        this.fkUser = fkUser;
        this.fkCategory = fkCategory;
        this.name = name;
        this.description = description;
        this.referencePrice = referencePrice;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(Integer fkCategory) {
        this.fkCategory = fkCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
    }
}
