package project.converter;

import project.entity.BlockEntity;
import project.entity.FeedbackEntity;
import project.entity.TransactionEntity;
import project.model.external.Feedback;
import project.model.internal.Transaction;

public class TransactionConverter {

    public static Transaction createTransactionFromEntity(TransactionEntity source) {
        Feedback feedback = FeedbackConverter.createFeedbackFromEntity(source.getValue());
        return new Transaction(source.getHash(), source.getUser(), source.getProductId(), feedback, source.getSignature());
    }

    public static TransactionEntity createTransactionEntity(Transaction source, FeedbackEntity feedbackEntity,
                                                            BlockEntity blockEntity) {
        return new TransactionEntity(source.getHash(), source.getUser(), source.getProductId(), feedbackEntity,
                source.getSignature(), blockEntity);
    }
}
