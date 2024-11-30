package com.home.shop.sales;

import com.home.shop.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;
    private final ItemRepository itemRepository;

//    ####API LIST####
    @PostMapping("/order/{id}")
    String order(@PathVariable Long id,
                 @RequestParam Integer count){

        if (count <= 0){
//            개수를 0으로 지정했을 때, 오류페이지로 전송(404)
            return "redirect:/";
        } else {
            /**주문내역 DB에 저장하기
             * 1. 상세페이지에서 갯수 지정 후 구매하기 누르면
             * 2. 서버에서 해당 아이템 id와 갯수 저장,
             * 3. 아이템 id 조회해서 가격저장
             * 4. 구매자 id 저장
             * 5. Service로 옮기자*/
            System.out.println("id: " + id + " / count: " + count);
            var result = itemRepository.findById(id);
            System.out.println("id == " + result);

            return "redirect:/list";
        }
    }
}
