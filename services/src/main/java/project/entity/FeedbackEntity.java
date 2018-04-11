package project.entity;

import javax.persistence.*;

@Entity
public class FeedbackEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "comment")
    private String comment;

    public FeedbackEntity(Integer mark, String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    public FeedbackEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
