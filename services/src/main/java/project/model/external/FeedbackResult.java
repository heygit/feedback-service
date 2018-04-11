package project.model.external;

import java.util.List;

public class FeedbackResult {

    private List<String> comments;
    private Double averageRating;

    public FeedbackResult(List<String> comments, Double averageRating) {
        this.comments = comments;
        this.averageRating = averageRating;
    }

    public FeedbackResult() {
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
