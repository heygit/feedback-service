package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.model.external.Feedback;
import project.model.external.FeedbackResult;
import project.model.internal.Account;
import project.model.internal.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private TranscationService transcationService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private AccountService accountService;

    @Transactional
    public void addFeedback(long userId, long productId, Feedback feedback) {
        Account account = accountService.getAccount(userId);
        Transaction transaction = transcationService.processTransaction(account, productId, feedback);
        blockService.addTransaction(transaction);
    }

    @Transactional(readOnly = true)
    public FeedbackResult getFeedbackResult(long productId) {
        List<Feedback> feedbacks = transcationService.getTransactions().stream()
                .filter(elem -> elem.getProductId() == productId)
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        List<String> comments = feedbacks.stream()
                .filter(elem -> elem.getComment() != null)
                .map(Feedback::getComment)
                .collect(Collectors.toList());

        FeedbackResult result = new FeedbackResult(comments, null);

        List<Integer> marks = feedbacks.stream()
                .filter(elem -> elem.getMark() != null)
                .map(Feedback::getMark)
                .collect(Collectors.toList());

        if (marks.isEmpty()) {
            return result;
        }

        double average = marks.stream().reduce(0, Integer::sum) / (double) marks.size();
        average = new BigDecimal(average, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP).doubleValue();
        result.setAverageRating(average);

        return result;
    }
}
