package challenge.blogapplicationapi.dto;

import challenge.blogapplicationapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPostResponse {
    private List<Post> listPost;
}
