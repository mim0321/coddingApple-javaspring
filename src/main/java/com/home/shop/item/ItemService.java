package com.home.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price, String user, String imgURL){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setUser(user);
        item.setImgURL(imgURL);

        itemRepository.save(item);
    }

}
