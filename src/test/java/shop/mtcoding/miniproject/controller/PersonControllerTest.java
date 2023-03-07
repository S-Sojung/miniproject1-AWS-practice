package shop.mtcoding.miniproject.controller;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import shop.mtcoding.miniproject.config.RedisConfigTest;
import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReqDto.PersonUpdateDto;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PersonControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void join_test() throws Exception {

        System.out.println("테스트 - join_test()");
        // given
        String requestBody = "name=aaa&email=aaa@nate.com&password=1234";

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/personJoin").content(requestBody)
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
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/personJoin2").content(requestBody)
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

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");
        System.out.println("이메일 : " + principal.getEmail());

        // then
        assertThat(principal.getEmail()).isEqualTo("ssar@nate.com");
        assertThat(principal.getPassword())
                .isEqualTo("9d85d697da8136003c67ea366b8c6a0225cb0f3ff95aca3e4634f0e09a8e6723");
        resultActions.andExpect(status().is3xxRedirection());
    }

    // 개인정보 수정하기 테스트

    @BeforeEach // Test메서드 실행 직전마다 호출된다
    public void setUp() {
        // 임시 세션 생성하기
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;
        // ObjectMapper om = new ObjectMapper(); @AutoWired 함!

        // String requestBody = "title=수정된 제목입니다&content=수정된 내용입니다";
        PersonUpdateDto personUpdateDto = new PersonUpdateDto();
        personUpdateDto.setName("소정");
        personUpdateDto.setPhone("01012345678");
        personUpdateDto.setAddress("소정시 희선구 주혜동");
        personUpdateDto.setPassword("9999");
        personUpdateDto.setSkills("Java");

        String requestBody = om.writeValueAsString(personUpdateDto);
        // System.out.println("테스트 : "+requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                put("/person/updateInfo")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession)); // session이 주입된 채로 요청

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.print("테스트: " + responseBody);

        // then

        // resultActions.andExpect(jsonPath("$.msg").value("회원 정보 수정 완료"));
        resultActions.andExpect(status().isCreated());
    }

}
