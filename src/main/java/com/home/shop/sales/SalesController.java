package com.home.shop.sales;

import com.home.shop.item.Item;
import com.home.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;
    private final SalesRepository salesRepository;

//    ####API LIST####

//    Order POST API(주문)
    @PostMapping("/order/{itemId}")
    String order(@PathVariable Long itemId,
                 @RequestParam Integer count,
                 @AuthenticationPrincipal CustomUser customUser){

        if (count <= 0){
            return "redirect:/";
        } else {
            salesService.saveSales(itemId, count, customUser.getId());

            return "redirect:/order/myordered";
        }
    }

    @GetMapping("/order/myordered")
    String orderpage(@AuthenticationPrincipal CustomUser customUser,
                     Model model){

        List<Sales> salesList = salesRepository.findByMemberId(customUser.getId());

        model.addAttribute("sales", salesList);

        return "myordered.html";
    }

}