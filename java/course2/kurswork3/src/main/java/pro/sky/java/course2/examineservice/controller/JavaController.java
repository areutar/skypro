package pro.sky.java.course2.examineservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaController {
    private final QuestionService javaQuestionService;

    public JavaController(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, String answer) {
        return javaQuestionService.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getAll() {
        return javaQuestionService.getAll();
    }

}
