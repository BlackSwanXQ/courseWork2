package courseWork.courseWork2.controller;


import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("exam/math")
public class MathQuestionController {
    public final QuestionService questionService;

    public MathQuestionController(@Qualifier("math") QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/add")
    public Question add(@RequestParam String question,
                        @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question,
                           @RequestParam String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @RequestMapping
    public Collection<Question> getAll() {
        return questionService.getAll();

    }
}
