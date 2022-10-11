package school.sptech.server.response;

import java.util.List;

public class DistResponse {
    private Double distance;
    private List cepsInfo;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List getCepsInfo() {
        return cepsInfo;
    }

    public void setCepsInfo(List cepsInfo) {
        this.cepsInfo = cepsInfo;
    }

    public DistResponse(Double distance, List cepsInfo) {
        this.distance = distance;
        this.cepsInfo = cepsInfo;
    }

    public DistResponse() {
    }

}
