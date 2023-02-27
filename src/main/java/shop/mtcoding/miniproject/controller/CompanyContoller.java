package shop.mtcoding.miniproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.miniproject.dto.post.PostResp.PostTitleRespDto;
import shop.mtcoding.miniproject.model.PostRespository;
import shop.mtcoding.miniproject.model.User;

@Controller
public class CompanyContoller {
    @Autowired
    private HttpSession session;
    @Autowired
    private PostRespository postRepository;

    public void companyMocLogin() {
        User user = new User();
        user.setId(2);
        user.setPInfoId(0);
        user.setCInfoId(1);
        user.setEmail("init@nate.com");
        user.setPassword("1234");

        session.setAttribute("principal", user);
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
        companyMocLogin();
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
    public String companyPosts(Model model) {
        companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        List<PostTitleRespDto> postTitleList = postRepository.findAllTitleByCInfoId(userPS.getCInfoId());
        model.addAttribute("postTitleList", postTitleList);
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
