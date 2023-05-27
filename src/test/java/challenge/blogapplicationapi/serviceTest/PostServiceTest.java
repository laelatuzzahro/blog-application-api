package challenge.blogapplicationapi.serviceTest;

import challenge.blogapplicationapi.model.Post;
import challenge.blogapplicationapi.repository.PostRepository;
import challenge.blogapplicationapi.service.PostService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PostService.class})
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Resource
    private PostRepository postRepository;

    @Test
    public void getAllPost_success() {
        postRepository.saveAndFlush(Post.builder().author("Afutami").title("Menjadi").body("Buku bacaan").build());
        when(postRepository.findAll()).thenReturn(List.of(Post.builder()
                .id(1L).author("afutami").title("Menjadi").body("Menjadi manusia").build()));
        var response = postService.getAllPost();
        Assertions.assertNotNull(response);
    }
}
