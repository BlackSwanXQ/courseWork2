package courseWork.courseWork2.controller;

import courseWork.courseWork2.interfaces.ExaminerService;
import courseWork.courseWork2.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


    @RestController
    @RequestMapping("exam/math/random")
    public class MathExamController {
        public final ExaminerService examinerService;

        public MathExamController(@Qualifier("mathExam") ExaminerService examinerService) {
            this.examinerService = examinerService;
        }

        @GetMapping("/{amount}")
        public Collection<Question> getQuestions(@PathVariable int amount) {
            return examinerService.getQuestions(amount);
        }
}
