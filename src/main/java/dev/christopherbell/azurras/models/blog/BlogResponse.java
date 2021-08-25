package dev.christopherbell.azurras.models.blog;

import dev.christopherbell.azurras.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private List<BlogPost> blogPostPayLoad;
    private List<String> blogTagPayload;
    private Message message;
    private String status;
}
