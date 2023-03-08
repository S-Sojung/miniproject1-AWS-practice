package shop.mtcoding.miniproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReqDto.PersonUpdateDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostRecommendIntegerRespDto;
import shop.mtcoding.miniproject.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PersonControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setPassword("9d85d697da8136003c67ea366b8c6a0225cb0f3ff95aca3e4634f0e09a8e6723");
        user.setSalt("bear");
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

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        // String sessionId = session.getId();
        User principal = (User) session.getAttribute("principal");
        // Set<String> sessionIds = redisTemplate.keys("spring:session:sessions:*");
        // redisTemplate.opsForHash().entries(sessionId);
        // System.out.println("테스트 : " + user.getId());

        // Map<Object, Object> sessionMap =
        // redisTemplate.opsForHash().entries(sessionId);

        // then
        assertThat(principal.getEmail()).isEqualTo("ssar@nate.com");
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

    @Test
    public void update_test() throws Exception {
        // given
        // String requestBody = "title=수정된 제목입니다&content=수정된 내용입니다";
        PersonUpdateDto personUpdateDto = new PersonUpdateDto();
        personUpdateDto.setName("소정");
        personUpdateDto.setPhone("01012345678");
        personUpdateDto.setBirthday("2001-04-10 00:00:00");
        personUpdateDto.setAddress("소정시 희선구 주혜동");
        personUpdateDto.setOriginPassword("1234");
        personUpdateDto.setPassword("9999");
        personUpdateDto.setSkills("Java");
        personUpdateDto.setEmail("ssar@nate.com");

        String requestBody = om.writeValueAsString(personUpdateDto);
        // System.out.println("테스트 : " + requestBody);

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
        resultActions.andExpect(status().isOk());
    }

    // 이력서 새로 쓰기 테스트
    @Test
    public void personInsertResumeForm_test() throws Exception {
        // given
        MockMultipartFile file = new MockMultipartFile("image", "profile1.jpg", "image/jpg",
                new FileInputStream("src/main/resources/static/images/profile1.jpg"));

        ResumeUpdateReqDto resumeUpdateReqDto = new ResumeUpdateReqDto();
        resumeUpdateReqDto.setProfile(file);
        resumeUpdateReqDto.setTitle("이력서샘플");
        resumeUpdateReqDto.setPortfolio("sampleGithub");
        resumeUpdateReqDto.setName("김샘플");
        resumeUpdateReqDto.setPhone("01088889999");
        resumeUpdateReqDto.setAddress("경기");
        resumeUpdateReqDto.setBirthday("2001-04-10 00:00:00");
        resumeUpdateReqDto.setPublish(true);
        resumeUpdateReqDto.setSelfIntro("안녕하세요");
        resumeUpdateReqDto.setSkills("Sql");

        // when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/person/resumes")
                .file("profile", file.getBytes())
                .param("title", resumeUpdateReqDto.getTitle())
                .param("portfolio", resumeUpdateReqDto.getPortfolio())
                .param("name", resumeUpdateReqDto.getName())
                .param("phone", resumeUpdateReqDto.getPhone())
                .param("address", resumeUpdateReqDto.getAddress())
                .param("selfIntro", resumeUpdateReqDto.getSelfIntro())
                .param("birthday", resumeUpdateReqDto.getBirthday())
                .param("skills", resumeUpdateReqDto.getSkills());

        ResultActions resultActions = mvc.perform(builder.session(mockSession));

        // String responseBody =
        // resultActions.andReturn().getResponse().getContentAsString();

        // then

        // resultActions.andExpect(jsonPath("$.msg").value("회원 정보 수정 완료"));
        resultActions.andExpect(status().is3xxRedirection());
    }

    // 이력서 수정 테스트
    @Test
    public void UpdateResumeForm_test() throws Exception {
        // given
        int id = 1;
        MockMultipartFile file = new MockMultipartFile("image", "profile1.jpg", "image/jpg",
                new FileInputStream("src/main/resources/static/images/profile1.jpg"));

        ResumeUpdateReqDto resumeUpdateReqDto = new ResumeUpdateReqDto();
        resumeUpdateReqDto.setProfile(file);
        resumeUpdateReqDto.setTitle("이력서샘플");
        resumeUpdateReqDto.setPortfolio("sampleGithub");
        resumeUpdateReqDto.setName("김샘플");
        resumeUpdateReqDto.setPhone("01088889999");
        resumeUpdateReqDto.setAddress("경기");
        resumeUpdateReqDto.setBirthday("2001-04-10 00:00:00");
        resumeUpdateReqDto.setPublish(true);
        resumeUpdateReqDto.setSelfIntro("안녕하세요");
        resumeUpdateReqDto.setSkills("Sql");

        // when

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/person/updateResume/" + id)
                .file("profile", file.getBytes())
                .param("title", resumeUpdateReqDto.getTitle())
                .param("portfolio", resumeUpdateReqDto.getPortfolio())
                .param("name", resumeUpdateReqDto.getName())
                .param("phone", resumeUpdateReqDto.getPhone())
                .param("address", resumeUpdateReqDto.getAddress())
                .param("selfIntro", resumeUpdateReqDto.getSelfIntro())
                .param("birthday", resumeUpdateReqDto.getBirthday())
                .param("skills", resumeUpdateReqDto.getSkills());

        ResultActions resultActions = mvc.perform(builder.session(mockSession));

        // then

        // resultActions.andExpect(jsonPath("$.msg").value("회원 정보 수정 완료"));
        resultActions.andExpect(status().is3xxRedirection());
    }

    // 이력서 지원하기 테스트
    @Test
    public void submitResume_test() throws Exception {
        // given
        int id = 4;

        String requestBody = "selectedResume=1";

        // when
        ResultActions resultActions = mvc.perform(post("/person/detail/" + id + "/resume").content(requestBody)
                .session(mockSession)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");

        // then
        assertThat(principal.getPInfoId()).isEqualTo(1);

        resultActions.andExpect(status().is3xxRedirection());
    }
}
