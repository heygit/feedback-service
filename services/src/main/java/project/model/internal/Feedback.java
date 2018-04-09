package project.model.internal;

public class Feedback {

    private int mark;
    private String comment;

    public Feedback(int mark, String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    public Feedback(int mark) {
        this.mark = mark;
    }

    public Feedback(String comment) {
        this.comment = comment;
    }

    public Feedback() {
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
