package com.home.shop.sales;

import com.home.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

//    ####API LIST####
    @PostMapping("/order/{itemId}")
    String order(@PathVariable Long itemId,
                 @RequestParam Integer count,
                 @AuthenticationPrincipal CustomUser customUser){

        if (count <= 0){
//            개수를 0으로 지정했을 때, 오류페이지로 전송(404)
            return "redirect:/";
        } else {
            salesService.saveSales(itemId, count, customUser.getId());

            return "redirect:/list";
        }
    }
}
