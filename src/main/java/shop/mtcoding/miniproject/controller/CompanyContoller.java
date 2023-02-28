package shop.mtcoding.miniproject.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.miniproject.dto.company.CompanyReq.JoinCompanyReqDto;
import shop.mtcoding.miniproject.dto.company.CompanyReq.LoginCompanyReqDto;
import shop.mtcoding.miniproject.dto.post.PostReq.PostSaveReqDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostTitleRespDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Company;
import shop.mtcoding.miniproject.model.CompanyRepository;
import shop.mtcoding.miniproject.model.Post;
import shop.mtcoding.miniproject.model.PostRespository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.model.UserRepository;
import shop.mtcoding.miniproject.service.CompanyService;
import shop.mtcoding.miniproject.service.PostService;

@Controller
public class CompanyContoller {

    @Autowired
    private HttpSession session;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRespository postRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PostService postService;

    // 인증에 필요한 일이기 때문에 company/login 이 아닌 이어서 했습니다.
    @GetMapping("/companyLoginForm")
    public String companyLoginForm() {
        return "company/loginForm";
    }

    @PostMapping("companyLogin")
    public String companyLogin(LoginCompanyReqDto loginCompanyReqDto) {

        if (loginCompanyReqDto.getEmail() == null ||
                loginCompanyReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 작성해주세요");
        }

        if (loginCompanyReqDto.getPassword() == null ||
                loginCompanyReqDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 작성해주세요");
        }

        User principal = userRepository.findByEmailAndPassword(loginCompanyReqDto.getEmail(),
                loginCompanyReqDto.getPassword());
        if (principal == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }

        session.setAttribute("principal", principal);

        return "redirect:/company/main";
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

        if (joinCompanyReqDto.getEmail() == null ||
                joinCompanyReqDto.getEmail().isEmpty()) {
            throw new CustomException("담당자 이메일을 작성해주세요");
        }

        if (joinCompanyReqDto.getPassword() == null ||
                joinCompanyReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }

        companyService.join(joinCompanyReqDto);
        return "redirect:/companyLoginForm";
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
    public String companyPosts(Model model) {
        // companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        List<PostTitleRespDto> postTitleList = postRepository.findAllTitleByCInfoId(userPS.getCInfoId());
        model.addAttribute("postTitleList", postTitleList);
        return "company/posts";
    }

    @GetMapping("/company/postDetail/{id}")
    public String personDetail(@PathVariable int id, Model model) {
        // companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        Post postPS = (Post) postRepository.findById(id);
        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        Skill skillPS = (Skill) skillRepository.findByPostId(id);
        StringTokenizer skills = new StringTokenizer(skillPS.getSkills(), ",");

        if (postPS.getCInfoId() != userPS.getCInfoId()) {
            throw new CustomException("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        // 공고 디테일 보기 //인증 및 권한체크
        model.addAttribute("post", postPS);
        model.addAttribute("company", companyPS);
        model.addAttribute("skills", skills);

        return "company/postDetail";
    }

    @GetMapping("/company/savePostForm")
    public String personSavePostForm(Model model) {
        // companyMocLogin();
        User userPS = (User) session.getAttribute("principal");
        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());

        model.addAttribute("company", companyPS);
        model.addAttribute("skills", Skill.madeSkills());
        return "company/savePostForm";
    }

    @PostMapping("/company/savePost")
    public String personSavePost(Model model, PostSaveReqDto PostSaveReqDto) {
        // companyMocLogin();
        User userPS = (User) session.getAttribute("principal");

        // postinsert skillinsert 동시 진행
        int id = postService.공고등록(PostSaveReqDto, userPS.getCInfoId());

        return "redirect:/company/postDetail/" + id; // +id
    }

}
