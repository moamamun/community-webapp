package com.reddit.clone.service;

import com.reddit.clone.dto.CommentsDto;
import com.reddit.clone.exceptions.PostNotFoundException;
import com.reddit.clone.mapper.CommentMapper;
import com.reddit.clone.model.Comment;
import com.reddit.clone.model.NotificationEmail;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.CommentRepository;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        //retrieve the email message, which then sends the notif
        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    //passing notification email (passing subject line followed by email and message of post author)
    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Comment on your post", user.getEmail(), message));
    }
}
