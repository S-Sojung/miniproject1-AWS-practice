package shop.mtcoding.miniproject.model;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.miniproject.config.RedisConfigTest;
import shop.mtcoding.miniproject.dto.post.PostResp.PostMainRespDto;

@MybatisTest
@Import(RedisConfigTest.class)
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void findAllWithCInfo_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();

        // when
        List<PostMainRespDto> postList = postRepository.findAllWithCInfo();
        String responseBody = om.writeValueAsString(postList);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(postList.get(0).getDeadline()).isBefore(postList.get(1).getDeadline());
        assertThat(postList.get(0).getTitle()).isEqualTo("열심히하는 개발자 구합니다.");
    }

}
