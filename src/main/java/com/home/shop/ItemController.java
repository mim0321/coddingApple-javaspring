//아래 클래스를 다른 파일에서도 쓰고싶으면 아래 package에 경로를 입력해줘야함 그래서 써놓는거임
package com.home.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);

        return "list.html";
    }

    /** 상품 추가기능 만들기
     * 1. 상품이름, 가격을 작성할 수 있는 페이지와 폼
     * 2. 전송을 누르면 서버에서 검사(예외처리)
     * 3. 문제 없을 시 DB에 저장하기
     * */

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

// 상품 추가 POST API
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public String addItem(@RequestParam String title,
                          @RequestParam Integer price,
                          Model model){
//        새로운 Item 객체 생성
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);

//        Item을 데이터베이스에 저장
        itemService.saveItem(item);

//        모델에 데이터 추가
        model.addAttribute("items", item);


        return "redirect:/list";
    }

//    상세페이지 GET API
//    URL parameters == url에 중괄호 + 작명 >> /{name}
    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
//        Optional 타입을 사용 할 때에는 findById의 결과가 null이 될 수 있기 때문에 if문을 사용하여 "해당 변수에 뭔가가 들어있으면 result.get()을 실행해줘" 라고 코딩하는 것이 옳다.
        if (result.isPresent()){
            model.addAttribute("item", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

}