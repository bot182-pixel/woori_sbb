package com.mysite.sbb;

import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SbbApplication {
    @GetMapping("/question/list")
    public static void main(String[] args) {
        SpringApplication.run(SbbApplication.class, args);
    }

    @Autowired
    private QuestionService questionService;
    private QuestionRepository questionRepository;

    void testJpa() {

    }

    void makeTestData() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:{%03d}", i);
            String content = "내용 없음";
            this.questionService.Create(subject, content);
        }
    }
}
