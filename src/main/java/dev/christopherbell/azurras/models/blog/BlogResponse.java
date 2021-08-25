package dev.christopherbell.azurras.models.blog;

import dev.christopherbell.azurras.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BlogResponse {
    @Autowired
    private List<BlogPost> blogPostPayLoad;
    @Autowired
    private List<String> blogTagPayload;
    @Autowired
    private Message message;
    @Autowired
    private String status;
}
