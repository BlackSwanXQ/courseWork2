package courseWork.courseWork2;

import ch.qos.logback.core.net.QueueFactory;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import courseWork.courseWork2.service.JavaQuestionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class CourseWork2Application {

    public static void main(String[] args) {
        SpringApplication.run(CourseWork2Application.class, args);

    }

}
