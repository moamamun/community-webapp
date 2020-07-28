package com.reddit.clone.service;

import com.reddit.clone.dto.CommentsDto;
import com.reddit.clone.exceptions.PostNotFoundException;
import com.reddit.clone.mapper.CommentMapper;
import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.repository.CommentRepository;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);
    }


}
