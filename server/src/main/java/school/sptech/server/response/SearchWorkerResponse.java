package school.sptech.server.response;

import school.sptech.server.model.Category;

import javax.persistence.ManyToOne;

public class SearchWorkerResponse {
    private String name;
    @ManyToOne
    private Category category;
    private Double distance;
    private int rating;
}
