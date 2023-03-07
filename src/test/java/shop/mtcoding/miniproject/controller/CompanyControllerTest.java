package shop.mtcoding.miniproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

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

import shop.mtcoding.miniproject.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

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
}
