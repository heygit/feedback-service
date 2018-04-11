package project.converter;

import project.entity.FeedbackEntity;
import project.model.external.Feedback;

public class FeedbackConverter {

    public static Feedback createFeedbackFromEntity(FeedbackEntity source) {
        return new Feedback(source.getMark(), source.getComment());
    }

    public static FeedbackEntity createFeedbackEntity(Feedback source) {
        return new FeedbackEntity(source.getMark(), source.getComment());
    }
}
