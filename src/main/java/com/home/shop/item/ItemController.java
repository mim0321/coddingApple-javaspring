//아래 클래스를 다른 파일에서도 쓰고싶으면 아래 package에 경로를 입력해줘야함 그래서 써놓는거임
package com.home.shop.item;

import com.home.shop.comment.Comment;
import com.home.shop.comment.CommentRepository;
import com.home.shop.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final ListService listService;
    private final S3Service s3Service;
    private final CommentRepository commentRepository;

    @GetMapping("/list")
    String list(Model model) {
        listService.listDB(model);
        return "redirect:/list/page/1";
    }

    //    List API
    @GetMapping("/list/page/{page}")
    String getListPage(Model model, @PathVariable Integer page) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(page - 1, 5));
        model.addAttribute("items", result);

//        totalPages를 List에 담기
        List<Integer> pageNumbers = IntStream.rangeClosed(1, result.getTotalPages())
                .boxed()
                .collect(Collectors.toUnmodifiableList());
        model.addAttribute("pages", pageNumbers);
        model.addAttribute("currentPage", page);
        return "list.html";
    }

    /**
     * 상품 추가기능 만들기
     * 1. 상품이름, 가격을 작성할 수 있는 페이지와 폼
     * 2. 전송을 누르면 서버에서 검사(예외처리)
     * 3. 문제 없을 시 DB에 저장하기
     */

//    write GET API
    @GetMapping("/write")
    String write(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "write.html";
        } else {
            return "redirect:/login";
        }
    }

    // 상품 추가 POST API
    @PostMapping("/add")
    public String addItem(@RequestParam String title,
                          @RequestParam Integer price,
                          @RequestParam String user,
                          @RequestParam String imgURL,
                          Model model) {
//        Service로 저장하는거 만듦
        itemService.saveItem(title, price, user, imgURL);
        System.out.println(imgURL);

        return "redirect:/list";
    }


    //    image post API
    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename) {
        var result = s3Service.createPresignedUrl("springS3Test/" + filename);

        return result;
    }

    //    상세페이지 GET API
//    URL parameters == url에 중괄호 + 작명 >> /{name}
    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        Optional<Item> result = itemRepository.findById(id);
//        Optional 타입을 사용 할 때에는 findById의 결과가 null이 될 수 있기 때문에 if문을 사용하여 "해당 변수에 뭔가가 들어있으면 result.get()을 실행해줘" 라고 코딩하는 것이 옳다.

        List<Comment> comments = commentRepository.findByParentId(id);

        if (result.isPresent()) {
            model.addAttribute("item", result.get());
            model.addAttribute("comments", comments);
            return "detail.html";
        } else {
            return "redirect:/list";
        }

    }

// 상품수정 API

    /**
     * 1. 수정버튼을 누르면
     * 2. 수정페이지로 이동하고(페이지엔 기존내용이 채워져있음)
     * 3. 전송 누르면 DB로 수정하면 됨
     */
    @GetMapping("/edit/{id}")
    String postEdit(@PathVariable Long id, Model model) {
        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("item", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }

    }

    @PostMapping("/update/{id}")
    String updateItem(@PathVariable Long id,
                      @RequestParam String title,
                      @RequestParam Integer price) {

        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            Item updatedItem = result.get();
            updatedItem.setTitle(title);
            updatedItem.setPrice(price);

            itemRepository.save(updatedItem);
        }
        return "redirect:/list";
    }


    //    삭제 API
    @DeleteMapping("/deleteItem")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.status(200).body("delete complete");
        } else {
            return ResponseEntity.status(400).body("400 error");
        }
    }



}