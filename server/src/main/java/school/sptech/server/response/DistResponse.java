package school.sptech.server.response;

import java.util.List;

public class DistResponse {
    private Double distance;
    private List<Object> cepsInfo;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Object> getCepsInfo() {
        return cepsInfo;
    }

    public void setCepsInfo(List<Object> cepsInfo) {
        this.cepsInfo = cepsInfo;
    }

    public DistResponse(Double distance, List<Object> cepsInfo) {
        this.distance = distance;
        this.cepsInfo = cepsInfo;
    }

    public DistResponse() {
    }

}
