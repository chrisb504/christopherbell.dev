package dev.christopherbell.post;

import dev.christopherbell.post.model.Post;
import dev.christopherbell.post.model.PostDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
  PostDetail toDetail(Post post);
}

