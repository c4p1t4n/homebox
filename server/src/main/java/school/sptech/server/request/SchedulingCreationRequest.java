package school.sptech.server.request;

public class SchedulingCreationRequest {
    private Integer fkUser;
    private Integer fkService;

    public SchedulingCreationRequest() {
    }

    public SchedulingCreationRequest(Integer fkUser, Integer fkService) {
        this.fkUser = fkUser;
        this.fkService = fkService;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkService() {
        return fkService;
    }

    public void setFkService(Integer fkService) {
        this.fkService = fkService;
    }
}
