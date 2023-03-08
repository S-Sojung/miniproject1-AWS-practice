package shop.mtcoding.miniproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateInfoDto;

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
        user.setPInfoId(0);
        user.setCInfoId(1);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
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
    public void companyUpdateResume_test() throws Exception {
        // given
        int id = 1;
        // ObjectMapper om = new ObjectMapper(); @AutoWired 함!

        // String requestBody = "title=수정된 제목입니다&content=수정된 내용입니다";
        CompanyUpdateInfoDto companyUpdateInfoDto = new CompanyUpdateInfoDto();
        companyUpdateInfoDto.setBossName("소정");
        companyUpdateInfoDto.setCyear("1999");
        companyUpdateInfoDto.setAddress("소정시 희선구 주혜동");
        companyUpdateInfoDto.setSize(4);
        companyUpdateInfoDto.setManagerName("소정2세");
        companyUpdateInfoDto.setManagerPhone("01011112222");
        companyUpdateInfoDto.setPassword("1234");

        String requestBody = om.writeValueAsString(companyUpdateInfoDto);
        // System.out.println("테스트 : "+requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                post("/company/updateInfo")
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
