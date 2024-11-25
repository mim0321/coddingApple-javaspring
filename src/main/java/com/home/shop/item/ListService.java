package com.home.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ItemRepository itemRepository;

    public void listDB(Model model){
    List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
    }
}
