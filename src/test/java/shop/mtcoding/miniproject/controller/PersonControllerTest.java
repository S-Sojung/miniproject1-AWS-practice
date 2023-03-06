package shop.mtcoding.miniproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void PersonJoinForm1Test() throws Exception {
        mvc.perform(get("/personJoinForm1"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/joinForm1"));
    }

    @Test
    public void PersonJoinForm2Test() throws Exception {
        mvc.perform(get("/personJoinForm2")
                .param("pInfoId", "1")) // 이전 단계에서 생성된 pInfoId 값을 파라미터로 전달
                .andExpect(status().isOk())
                .andExpect(view().name("person/joinForm2"));
    }

    @Test
    public void join_test() throws Exception {
        mvc.perform(post("/personJoin")
                .param("name", "testName")
                .param("password", "testPassword")
                .param("email", "testEmail@test.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/personJoinForm2"));
    }

    @Test
    public void join_test2() throws Exception {
        mvc.perform(post("/personJoin2")
                .param("skills", "java", "spring", "hibernate")
                .param("pInfoId", "1")) // 이전 단계에서 생성된 pInfoId 값을 파라미터로 전달
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/personLoginForm"));
    }

    @Test
    public void testJoin(JoinPersonReqDto joinPersonReqDto) throws Exception {
        // 1. 1번째 페이지에서 회원 정보를 등록.
        MvcResult result = mvc.perform(post("/personJoin")
                .param("name", "testName")
                .param("password", "testPassword")
                .param("email", "testEmail@test.com"))

                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/personJoinForm2"))
                .andReturn();

        // 2. 1번째 페이지에서 받은 pInfoId를 가져옴.
        MockHttpServletRequest request = result.getRequest();
        int pInfoId = (int) request.getAttribute("pInfoId");

        // 3. 2번째 페이지에서 회원이 선택한 정보를 등록.
        mvc.perform(post("/personJoin2")
                .param("skills", "Java", "Sql") // 회원이 선택한 기술 스택 정보
                .param("pInfoId", Integer.toString(pInfoId))) // 1번째 페이지에서 받은 회원 ID
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/personLoginForm"));

        // 4. 등록된 회원 정보가 DB에 정상적으로 저장되었는지 확인.
        Person person = personRepository.findById(pInfoId);
        assertEquals("testName", person.getName());
        assertEquals("testEmail@test.com", person.getEmail());
        assertEquals("Java,Python", person.getSkills());
    }
    // @Test
    // public void join_test() throws Exception {

    // System.out.println("테스트 - join_test()");
    // // given
    // String requestBody = "name=aaa&email=aaa@nate.com&password=1234";

    // // when
    // ResultActions resultActions =
    // mvc.perform(MockMvcRequestBuilders.post("/join").content(requestBody)
    // .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

    // // then
    // resultActions.andExpect(status().is3xxRedirection());

    // }
    @Test
    public void personLogin_test() throws Exception {
        // given
        String requestBody = "email=ssar@nate.com&password=1234";

        // when
        ResultActions resultActions = mvc.perform(post("/personLogin").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");
        // System.out.println(principal.getUsername());

        // then
        assertThat(principal.getEmail()).isEqualTo("ssar@nate.com");
        assertThat(principal.getPassword()).isEqualTo("1234");
        resultActions.andExpect(status().is3xxRedirection());
    }

}
