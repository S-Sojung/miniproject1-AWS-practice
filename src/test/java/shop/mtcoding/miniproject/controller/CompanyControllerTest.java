package shop.mtcoding.miniproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import shop.mtcoding.miniproject.dto.Resume.ResumeRes.ResumeWithPostInfoRecommendDto;
import shop.mtcoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateInfoDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalReq.CompanyProposalStatusReqDto;
import shop.mtcoding.miniproject.dto.post.PostReq.PostUpdateReqDto;
import shop.mtcoding.miniproject.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @Autowired
    private ObjectMapper om;

    @BeforeEach // Test메서드 실행 직전마다 호출된다
    public void setUp() {
        // 임시 세션 생성하기
        User user = new User();
        user.setId(3);
        user.setPassword("ad38f305434fb803fbadb9cf57df1e822bff382352c19dc67b5b13055a049cd6");
        user.setEmail("init@nate.com");
        user.setSalt("cat");
        user.setPInfoId(0);
        user.setCInfoId(1);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void insertPost_test() throws Exception {
        // given
        String requestBody = "title=안녕&career=3&pay=3000만원&cIntro=안녕&condition=계약직&" +
                "startHour=09:00:00&endHour=18:00:00&deadline=2023-04-10&jobIntro=gd&skills=Java&skills=Sql";

        // when
        ResultActions resultActions = mvc.perform(post("/company/savePost")
                .content(requestBody)
                .session(mockSession)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void companyRecommend_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/company/recommend").session(mockSession));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<ResumeWithPostInfoRecommendDto> resumeRecommendList = (List<ResumeWithPostInfoRecommendDto>) map
                .get("postInfoAndResumes");
        String resumeJson = om.writeValueAsString(resumeRecommendList);
        System.out.println("테스트" + resumeJson);
        // then
        assertThat(resumeRecommendList.get(0).getResumes().get(0).getSkills()).contains("Java");
    }

    @Test
    public void updatePost_test() throws Exception {
        // given
        int id = 1;
        PostUpdateReqDto postUpdateDto = new PostUpdateReqDto();
        postUpdateDto.setTitle("안녕");
        postUpdateDto.setCareer(3);
        postUpdateDto.setComIntro("안녕");
        postUpdateDto.setCondition("계약직");
        postUpdateDto.setDeadline("2023-04-10");
        postUpdateDto.setStartHour("09:00:00");
        postUpdateDto.setEndHour("19:00:00");
        postUpdateDto.setJobIntro("ㅎㅇ");
        postUpdateDto.setPay("3000만원");
        postUpdateDto.setSkills("Java,Sql");

        String requestBody = om.writeValueAsString(postUpdateDto);

        // when
        ResultActions resultActions = mvc.perform(put("/company/updatePost/" + id)
                .content(requestBody)
                .session(mockSession)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void deletePost_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(delete("/company/deletePost/" + id)
                .session(mockSession));

        // then
        resultActions.andExpect(jsonPath("$.msg").value("공고 삭제 성공"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void proposal_test() throws Exception {
        // given
        int id = 4;
        CompanyProposalStatusReqDto status = new CompanyProposalStatusReqDto();
        status.setStatusCode(1);

        String requestBody = om.writeValueAsString(status);
        // when
        ResultActions resultActions = mvc.perform(put("/company/proposal/" + id)
                .content(requestBody)
                .session(mockSession)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void join_test() throws Exception {
        System.out.println("테스트 - company_join_test()");
        // given
        String requestBody = "name=green&address=green3F&number=1&managerName=sj&email=green@nate.com&password=1234";

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/companyJoin").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void companyLogin_test() throws Exception {
        // given
        String requestBody = "email=init@nate.com&password=1234";

        // when
        ResultActions resultActions = mvc.perform(post("/companyLogin").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");
        // System.out.println(principal.getUsername());

        // then
        assertThat(principal.getEmail().equals("init@nate.com"));
        assertThat(principal.getPassword().equals("ad38f305434fb803fbadb9cf57df1e822bff382352c19dc67b5b13055a049cd6"));
        resultActions.andExpect(status().is3xxRedirection());
    }

    // 회사 정보 수정하기 테스트
    @Test
    public void companyUpdateInfo_test() throws Exception {
        // given
        MockMultipartFile file = new MockMultipartFile("image", "profile1.jpg", "image/jpg",
                new FileInputStream("src/main/resources/static/images/profile1.jpg"));
        CompanyUpdateInfoDto companyUpdateInfoDto = new CompanyUpdateInfoDto();
        companyUpdateInfoDto.setCyear("1999");
        companyUpdateInfoDto.setBossName("aaaa");
        companyUpdateInfoDto.setAddress("소정시 희선구 주혜동");
        companyUpdateInfoDto.setSize(4);
        companyUpdateInfoDto.setManagerName("소정2세");
        companyUpdateInfoDto.setManagerPhone("01011112222");
        companyUpdateInfoDto.setOriginPassword("1234");
        companyUpdateInfoDto.setPassword("4444");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/company/updateInfo")
                .file("logo", file.getBytes())
                .param("cyear", companyUpdateInfoDto.getCyear())
                .param("bossName", companyUpdateInfoDto.getBossName())
                .param("Address", companyUpdateInfoDto.getAddress())
                .param("size", String.valueOf(companyUpdateInfoDto.getSize()))
                .param("managerName", companyUpdateInfoDto.getManagerName())
                .param("managerPhone", companyUpdateInfoDto.getManagerPhone())
                .param("originPassword", companyUpdateInfoDto.getOriginPassword())
                .param("password", companyUpdateInfoDto.getPassword());

        ResultActions resultActions = mvc.perform(builder.session(mockSession));
        // then

        // resultActions.andExpect(jsonPath("$.msg").value("회원 정보 수정 완료"));
        resultActions.andExpect(status().isOk());
    }
}
