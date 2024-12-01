package com.home.shop.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public void saveSales(Long itemId, Integer count, Long memberId){
        Sales sales = new Sales();
        sales.setItemId(itemId);
        sales.setCount(count);
        sales.setMemberId(memberId);

        salesRepository.save(sales);

    }

}
