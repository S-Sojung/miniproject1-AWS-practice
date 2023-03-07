package shop.mtcoding.miniproject.model;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.miniproject.config.RedisConfigTest;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Import(RedisConfigTest.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonProposalRepository personProposalRepository;

    @Test
    public void findByPInfoIdAndPostId_test() throws Exception {
        // given
        int pInfoId = 1;
        int postId = 1;
        ObjectMapper om = new ObjectMapper();

        // when
        PersonProposal proposal = personProposalRepository.findByPInfoIdAndPostId(pInfoId, postId);
        System.out.println("테스트 1 : " + proposal.getPInfoId());
        System.out.println("테스트 2 : " + proposal.getPostId());

        String responseBody = om.writeValueAsString(proposal);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(proposal.getPostId()).isEqualTo(1);
    }

}
