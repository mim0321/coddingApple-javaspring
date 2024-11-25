package com.home.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //    댓글 API
    @PostMapping("/comment/{parentId}")
    String postComment(
            @RequestParam String username,
            @RequestParam String content,
            @PathVariable Long parentId
    ) {

        commentService.saveComment(username, content, parentId);

        return "redirect:/detail/" + parentId;
    }

}
