package com.reddit.clone.mapper;

import com.reddit.clone.dto.PostRequest;
import com.reddit.clone.dto.PostResponse;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "description", source = "description")
    PostResponse mapToDto(Post post);
}
