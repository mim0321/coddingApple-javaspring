//아래 클래스를 다른 파일에서도 쓰고싶으면 아래 package에 경로를 입력해줘야함 그래서 써놓는거임
package com.home.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkRepository homeworkRepository;

    @GetMapping("/hw")
    String hw(Model model){
        List<Homework> result = homeworkRepository.findAll();
        model.addAttribute("hw", result);

        return "hw.html";
    }

}