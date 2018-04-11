package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.model.external.Feedback;
import project.model.external.FeedbackResult;
import project.service.FeedbackService;
import project.service.ProductService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static project.constants.ParamNames.*;

@Controller
@RequestMapping("/api/v1/feedbackManagement")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFeedbacksByProduct(@PathVariable("id") long productId) {
        return Collections.singletonMap(FEEDBACK_RESULT_KEY, feedbackService.getFeedbackResult(productId));
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> postFeedback(@PathVariable("id") long productId, @RequestBody Feedback feedback) {
        long userId = 1;
        feedbackService.addFeedback(userId, productId, feedback);
        return Collections.emptyMap();
    }
}
