package com.home.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item saveItem(Item item){
        return itemRepository.save(item);
    }
}
