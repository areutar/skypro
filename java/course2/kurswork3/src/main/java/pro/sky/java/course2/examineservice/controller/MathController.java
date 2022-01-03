package pro.sky.java.course2.examineservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.service.impl.MathQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/math")
public class MathController {
    private final MathQuestionService mathQuestionService;

    public MathController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return mathQuestionService.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, String answer) {
        return mathQuestionService.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getAll() {
        return mathQuestionService.getAll();
    }

}
