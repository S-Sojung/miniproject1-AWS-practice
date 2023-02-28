package shop.mtcoding.miniproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shop.mtcoding.miniproject.dto.company.CompanyReq;
import shop.mtcoding.miniproject.dto.company.CompanyReq.JoinCompanyReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.LoginPersonReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.service.CompanyService;
import shop.mtcoding.miniproject.service.PersonService;

@Controller
public class CompanyContoller {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompanyService companyService;

    // 인증에 필요한 일이기 때문에 company/login 이 아닌 이어서 했습니다.
    @GetMapping("/companyLoginForm")
    public String companyLoginForm() {
        return "company/loginForm";
    }

    @PostMapping("/companyLogin")
    public String companyLogin() {
    }

    @GetMapping("/companyJoinForm")
    public String companyJoinForm1(Model model) {
        model.addAttribute("companyReq", new JoinCompanyReqDto()); // UserForm: 사용자 정보를 담는 모델 클래스
        return "company/joinForm";
    }

    @PostMapping("/companyJoin")
    public String join(JoinCompanyReqDto joinCompanyReqDto) {

        if (joinCompanyReqDto.getName() == null ||
                joinCompanyReqDto.getName().isEmpty()) {
            throw new CustomException("회사명을 작성해주세요");
        }
        if (joinCompanyReqDto.getAddress() == null ||
                joinCompanyReqDto.getAddress().isEmpty()) {
            throw new CustomException("회사 주소를 작성해주세요");
        }
        if (joinCompanyReqDto.getNumber() == null ||
                joinCompanyReqDto.getNumber().isEmpty()) {
            throw new CustomException("사업자 번호를 작성해주세요");
        }

        if (joinCompanyReqDto.getManagerName() == null ||
                joinCompanyReqDto.getManagerName().isEmpty()) {
            throw new CustomException("담당자 성함을 작성해주세요");
        }

        if (joinCompanyReqDto.getManagerEmail() == null ||
                joinCompanyReqDto.getManagerEmail().isEmpty()) {
            throw new CustomException("담당자 이메일을 작성해주세요");
        }

        if (joinCompanyReqDto.getPassword() == null ||
                joinCompanyReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }

        companyService.join(joinCompanyReqDto);
        return "redirect:/company/loginForm";
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
