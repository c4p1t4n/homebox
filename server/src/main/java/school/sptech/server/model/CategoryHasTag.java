package school.sptech.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CategoryHasTag {
    @Id
    @Column(name = "id_category_has_tag")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Tag tag;

    public CategoryHasTag() {
    }

    public CategoryHasTag(Category category, Tag tag) {
        this.category = category;
        this.tag = tag;
    }

    public CategoryHasTag(Integer id, Category category, Tag tag) {
        this.id = id;
        this.category = category;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
