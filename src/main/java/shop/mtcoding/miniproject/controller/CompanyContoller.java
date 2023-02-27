package shop.mtcoding.miniproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/personJoin")
    public String join(JoinPersonReqDto joinPersonReqDto) {

        System.out.println("테스트 : " + joinPersonReqDto.getName());
        System.out.println("테스트 : " + joinPersonReqDto.getPassword());

        if (joinPersonReqDto.getName() == null ||
                joinPersonReqDto.getName().isEmpty()) {
            throw new CustomException("name을 작성해주세요");
        }
        if (joinPersonReqDto.getPassword() == null ||
                joinPersonReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        if (joinPersonReqDto.getEmail() == null ||
                joinPersonReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }
        personService.회원가입(joinPersonReqDto);

        // Person 인서트를 이름만!
        // Person 인서트한 id 값을 유저에게 인서트하기

        return "redirect:/personJoinForm2";
    }

    @PostMapping("/personJoin2")
    public String join() {
        return "";
    }

    @PostMapping("/personLogin")
    public String personLoginForm(LoginPersonReqDto loginPersonReqDto) {

        System.out.println("테스트: " + loginPersonReqDto.getEmail());
        System.out.println("테스트: " + loginPersonReqDto.getPassword());

        if (loginPersonReqDto.getEmail() == null ||
                loginPersonReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }
        if (loginPersonReqDto.getPassword() == null ||
                loginPersonReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }

        User principal = personRepository.findByEmailAndPassword(loginPersonReqDto.getEmail(),
                loginPersonReqDto.getPassword());
        if (principal == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }

        session.setAttribute("principal", principal);

        return "redirect:/person/main";
    }

    // 인증에 필요한 일이기 때문에 company/login 이 아닌 이어서 했습니다.
    @GetMapping("/companyLoginForm")
    public String companyLoginForm() {
        return "company/loginForm";
    }

    @GetMapping("/companyJoinForm1")
    public String companyJoinForm1() {
        return "company/joinForm1";
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
