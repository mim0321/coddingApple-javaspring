package com.home.shop.sales;

import com.home.shop.item.ItemRepository;
import com.home.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public void saveSales(Long itemId, Integer count, Long memberId){
        Sales sales = new Sales();

        var item = itemRepository.findById(itemId)
                        .orElseThrow(() -> new RuntimeException("Item not found with Id: " + itemId));

        var member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new RuntimeException("Item not found with Id: " + itemId));

        sales.setItem(item);
        sales.setCount(count);
        sales.setMember(member);

        salesRepository.save(sales);

    }

}
