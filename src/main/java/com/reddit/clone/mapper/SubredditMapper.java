package com.reddit.clone.mapper;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    //MaptoDTO takes subreddit object as input and returns subredditdto object
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    //takes list of  posts as input and returns the size
    default Integer mapPosts(List<Post> numberOfPosts) { return numberOfPosts.size(); }

    //Create Mappings Similar to mapsubreddit to dto method,
    //instead create mappings from subreddit DTO to subreddit entity
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
