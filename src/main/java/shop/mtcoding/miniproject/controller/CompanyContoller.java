package shop.mtcoding.miniproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.miniproject.dto.company.CompanyReq.JoinCompanyReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.LoginPersonReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.service.PersonService;

@Controller
public class CompanyContoller {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    // public void companyMocLogin() {
    // User user = new User();
    // user.setId(2);
    // user.setPInfoId(0);
    // user.setCInfoId(1);
    // user.setEmail("init@nate.com");
    // user.setPassword("1234");

    // session.setAttribute("principal", user);
    // }

    // 인증에 필요한 일이기 때문에 company/login 이 아닌 이어서 했습니다.
    @GetMapping("/companyLoginForm")
    public String companyLoginForm() {
        return "company/loginForm";
    }

    @GetMapping("/companyJoinForm1")
    public String companyJoinForm1() {
        return "company/joinForm1";
    }

    @PostMapping("/companyJoin1")
    public String join(JoinCompanyReqDto joinCompanyReqDto) {

        System.out.println("테스트 : " + joinCompanyReqDto.getName());
        System.out.println("테스트 : " + joinCompanyReqDto.getPassword());

        if (joinCompanyReqDto.getName() == null ||
                joinCompanyReqDto.getName().isEmpty()) {
            throw new CustomException("회사명을 작성해주세요");
        }
        if (joinCompanyReqDto.getAddress() == null ||
                joinCompanyReqDto.getAddress().isEmpty()) {
            throw new CustomException("회사 주소를 작성해주세요");
        }
        if (joinCompanyReqDto.getNumber() == null ||
                joinCompanyReqDto.getNumber() == 0) {
            throw new CustomException("사업자 번호를 작성해주세요");
        }
        // companyService.회원가입(JoinCompanyReqDto);

        return "redirect:/company/joinForm2";
    }

    @GetMapping("/companyJoinForm2")
    public String companyJoinForm2() {
        // jsp에서 받은 값을 여기에 들고와서 넘겨줘야함
        return "company/joinForm2";
    }

    @GetMapping({ "/company/main", "/company" })
    public String companyMain() {
        // companyMocLogin();
        return "company/main";
    }

    @GetMapping("/company/getResume")
    public String companyGetResume() {
        return "company/getResume";
    }

    @GetMapping("/company/recommend")
    public String companyRecommend() {
        return "company/recommend";
    }

    @GetMapping("/company/info")
    public String companyInfo() {
        return "company/info";
    }

    @GetMapping("/company/updateInfoForm")
    public String companyUpdateInfoForm() {
        return "company/updateInfoForm";
    }

    @GetMapping("/company/scrap")
    public String companyScrap() {
        return "company/scrap";
    }

    @GetMapping("/company/posts")
    public String companyPosts() {
        return "company/posts";
    }

    @GetMapping("/company/postDetail")
    public String personDetail() {

        return "company/postDetail";
    }

    @GetMapping("/company/savePostForm")
    public String personSavePostForm() {

        return "company/savePostForm";
    }

}
