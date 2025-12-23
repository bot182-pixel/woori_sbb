package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    //    private final QuestionRepository questionRepository;
    private final QuestionService questionService;  // questionService 객체로 수정
    private final ContentNegotiationManager contentNegotiationManager;

    //    @GetMapping("/question/list")
//    @ResponseBody
//    public String list(Model model) {
//        List<Question> questionList = this.questionService.getList();   //this.questionService 가 대신 역할을 하도록 수정
//        model.addAttribute("questionList", questionList);
//        return "question_list";
//    }

    @GetMapping
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")int page) {
        Page<Question> paging = this.questionService.getList(page);   //this.questionService 가 대신 역할을 하도록 수정
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping
    public String detail(Model model,
                         @PathVariable Integer id,
                         AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

//    @GetMapping("/create")
//    public String questionCreate() {
//        return "question_form";
//    }

    @GetMapping
    public String questionCreate(@RequestParam(value = "subject") String subject,
                                 @RequestParam(value = "content") String content) {
        this.questionService.Create(subject, content);
        return String.format("redirect:/question/list");
    }

    @GetMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        this.questionService.Create(questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/list";
    }
}

