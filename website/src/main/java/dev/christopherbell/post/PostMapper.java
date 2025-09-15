package dev.christopherbell.post;

import dev.christopherbell.post.model.Post;
import dev.christopherbell.post.model.PostDetail;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for converting {@link Post}
 * entities to {@link PostDetail} DTOs.
 */
@Mapper(componentModel = "spring")
public interface PostMapper {
  /**
   * Maps a persisted {@code Post} to a {@code PostDetail} for API responses.
   *
   * @param post the source post entity
   * @return the mapped detail DTO
   */
  PostDetail toDetail(Post post);
}

