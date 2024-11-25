package com.home.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(String username, String content, Long parentId){
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setContent(content);
        comment.setParentId(parentId);

        commentRepository.save(comment);
    }
}
