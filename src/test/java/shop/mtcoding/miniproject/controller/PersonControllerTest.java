package shop.mtcoding.miniproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.miniproject.dto.post.PostResp.PostRecommendIntegerRespDto;
import shop.mtcoding.miniproject.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @Autowired
    private ObjectMapper om;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setPassword("9d85d697da8136003c67ea366b8c6a0225cb0f3ff95aca3e4634f0e09a8e6723");
        user.setEmail("ssar@nate.com");
        user.setPInfoId(1);
        user.setCInfoId(0);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void join_test() throws Exception {

        System.out.println("테스트 - join_test()");
        // given
        String requestBody = "name=aaa&email=aaa@nate.com&password=1234";

        // when
        ResultActions resultActions = mvc.perform(post("/personJoin").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void join2_test() throws Exception {

        System.out.println("테스트 - join2_test()");
        // given

        // String requestBody = "skills=Java&skills=Sql";
        String requestBody = "skills=Java&skills=Sql&pInfoId=1";

        // when
        ResultActions resultActions = mvc.perform(post("/personJoin2").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    // 로그인 테스트
    @Test
    public void personLogin_test() throws Exception {
        // given
        String requestBody = "email=ssar@nate.com&password=1234";

        // when
        ResultActions resultActions = mvc.perform(post("/personLogin").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // 세션이 비어있으면 레디스 확인해서 레디스에 있는 값을 세션에 넣음 ...
        // 레디스 세션에 다이렉트하게 갈 수 없음.
        // HttpSession session = resultActions.andReturn().getRequest().getSession();
        // String sessionId = session.getId();

        // Set<String> sessionIds = redisTemplate.keys("spring:session:sessions:*");
        // redisTemplate.opsForHash().entries(sessionId);
        // System.out.println("테스트 : " + sessionId);

        // Map<Object, Object> sessionMap =
        // redisTemplate.opsForHash().entries(sessionId);

        // then
        // assertThat(principal.getEmail()).isEqualTo("ssar@nate.com");
        // assertThat(principal.getPassword())
        // .isEqualTo("ad38f305434fb803fbadb9cf57df1e822bff382352c19dc67b5b13055a049cd6");
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void personRecommend_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/person/recommend").session(mockSession));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<PostRecommendIntegerRespDto> postRecommendList = (List<PostRecommendIntegerRespDto>) map.get("postList");
        String postJson = om.writeValueAsString(postRecommendList);
        System.out.println("테스트" + postJson);
        // then
        assertThat(postRecommendList.get(0).getTitle()).isEqualTo("멋진 개발자 구합니다.");
    }

}
