package courseWork.courseWork2.controller;

import courseWork.courseWork2.interfaces.ExaminerService;
import courseWork.courseWork2.question.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("exam/java/random")
public class ExamController {
    public final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }


}
