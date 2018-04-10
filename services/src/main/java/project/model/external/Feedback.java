package project.model.external;

public class Feedback {

    private Integer mark;
    private String comment;

    public Feedback(Integer mark, String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    public Feedback() {
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
