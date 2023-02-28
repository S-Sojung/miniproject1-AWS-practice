package shop.mtcoding.miniproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.miniproject.dto.ResponseDto;
import shop.mtcoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateInfoDto;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.miniproject.dto.company.CompanyReq.JoinCompanyReqDto;
import shop.mtcoding.miniproject.dto.company.CompanyReq.LoginCompanyReqDto;

import shop.mtcoding.miniproject.dto.post.PostReq.PostSaveReqDto;
import shop.mtcoding.miniproject.dto.post.PostReq.PostUpdateReqDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostTitleRespDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
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
    private UserRepository userRepository;

    @Autowired
    private PostRespository postRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private CompanyService companyService;

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
    public String companyInfo(Model model) {
        companyMocLogin();
        User principal = (User) session.getAttribute("principal");
        Company companyPS = companyRepository.findById(principal.getCInfoId());
        model.addAttribute("companyPS", companyPS);
        return "company/info";
    }

    @GetMapping("/company/updateInfoForm")
    public String companyUpdateInfoForm(Model model) {
        companyMocLogin();
        User principal = (User) session.getAttribute("principal");
        Company companyPS = companyRepository.findById(principal.getCInfoId());
        model.addAttribute("companyPS", companyPS);
        return "company/updateInfoForm";
    }

    @PostMapping("/company/updateInfo")
    public ResponseEntity<?> companyUpdateInfo(@ModelAttribute CompanyUpdateInfoDto companyUpdateInfoDto)
            throws IOException {
        companyMocLogin();
        if (companyUpdateInfoDto.getBossName() == null || companyUpdateInfoDto.getBossName().isEmpty()) {
            throw new CustomApiException("대표자명을 확인해주세요");
        }
        if (companyUpdateInfoDto.getAddress() == null || companyUpdateInfoDto.getAddress().isEmpty()) {
            throw new CustomApiException("주소를 확인해주세요");
        }
        if (companyUpdateInfoDto.getCyear() == null || companyUpdateInfoDto.getCyear().isEmpty()) {
            throw new CustomApiException("설립년도를 확인해주세요");
        }
        if (companyUpdateInfoDto.getManagerName() == null || companyUpdateInfoDto.getManagerName().isEmpty()) {
            throw new CustomApiException("담당자 이름을 확인해주세요");
        }
        if (companyUpdateInfoDto.getManagerPhone() == null || companyUpdateInfoDto.getManagerPhone().isEmpty()) {
            throw new CustomApiException("담당자 번호를 확인해주세요");
        }
        if (companyUpdateInfoDto.getSize() == null) {
            throw new CustomApiException("사원수를 확인해주세요");
        }
        companyService.updateInfo(companyUpdateInfoDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "기업 정보 수정 완료", null), HttpStatus.OK);
    }

    @GetMapping("/company/scrap")
    public String companyScrap() {
        return "company/scrap";
    }

    @GetMapping("/company/posts")
    public String companyPosts(Model model) {
        // companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.FORBIDDEN);
        }

        List<PostTitleRespDto> postTitleList = postRepository.findAllTitleByCInfoId(userPS.getCInfoId());
        model.addAttribute("postTitleList", postTitleList);
        return "company/posts";
    }

    @GetMapping("/company/postDetail/{id}")
    public String companyDetail(@PathVariable int id, Model model) {
        companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.FORBIDDEN);
        }

        Post postPS = (Post) postRepository.findById(id);
        if (postPS == null) {
            throw new CustomException("없는 공고 입니다.");
        }
        if (postPS.getCInfoId() != userPS.getCInfoId()) {
            throw new CustomException("게시글을 볼 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        Skill skillPS = (Skill) skillRepository.findByPostId(id);
        StringTokenizer skills = new StringTokenizer(skillPS.getSkills(), ",");

        // 공고 디테일 보기 //인증 및 권한체크
        model.addAttribute("post", postPS);
        model.addAttribute("company", companyPS);
        model.addAttribute("skills", skills);

        return "company/postDetail";
    }

    @GetMapping("/company/updatePostForm/{id}")
    public String companyUpdatePost(@PathVariable int id, Model model) {
        companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.FORBIDDEN);
        }

        Post postPS = (Post) postRepository.findById(id);
        if (postPS == null) {
            throw new CustomException("없는 공고 입니다.");
        }
        if (postPS.getCInfoId() != userPS.getCInfoId()) {
            throw new CustomException("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        Skill skillPS = (Skill) skillRepository.findByPostId(id);

        // 공고 디테일 보기 //인증 및 권한체크
        model.addAttribute("post", postPS);
        model.addAttribute("company", companyPS);
        model.addAttribute("skillsPS", skillPS.getSkills());
        model.addAttribute("skills", Skill.madeSkills());
        return "company/updatePostForm";
    }

    @PutMapping("/company/updatePost/{id}")
    public @ResponseBody ResponseEntity<?> companyUpdatePost(@PathVariable int id,
            @RequestBody PostUpdateReqDto postUpdateReqDto) {
        companyMocLogin();
        User userPS = (User) session.getAttribute("principal");

        if (postUpdateReqDto.getTitle() == null ||
                postUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("title 작성해주세요");
        }
        if (postUpdateReqDto.getPay() == null ||
                postUpdateReqDto.getPay().isEmpty()) {
            throw new CustomApiException("연봉 작성해주세요");
        }
        if (postUpdateReqDto.getCareer() == null) {
            throw new CustomApiException("지원 자격 작성해주세요");
        }
        if (postUpdateReqDto.getPay() == null ||
                postUpdateReqDto.getPay().isEmpty()) {
            throw new CustomApiException("연봉 작성해주세요");
        }
        if (postUpdateReqDto.getCondition() == null ||
                postUpdateReqDto.getCondition().isEmpty()) {
            throw new CustomApiException("근무조건 작성해주세요");
        }
        if (postUpdateReqDto.getDeadline() == null ||
                postUpdateReqDto.getDeadline().isEmpty()) {
            throw new CustomApiException("마감시간 작성해주세요");
        }
        if (postUpdateReqDto.getStartHour() == null ||
                postUpdateReqDto.getStartHour().isEmpty() || (postUpdateReqDto.getEndHour() == null ||
                        postUpdateReqDto.getEndHour().isEmpty())) {
            throw new CustomApiException("근무 시간을 작성해주세요");
        }
        if (postUpdateReqDto.getJobIntro() == null ||
                postUpdateReqDto.getJobIntro().isEmpty()) {
            throw new CustomApiException("업무소개를 작성해주세요");
        }
        System.out.println(postUpdateReqDto.getComIntro());
        if (postUpdateReqDto.getComIntro() == null ||
                postUpdateReqDto.getComIntro().isEmpty()) {
            throw new CustomApiException("기업소개를 작성해주세요");
        }
        if (postUpdateReqDto.getTitle().length() >= 20) {
            throw new CustomApiException("title의 길이가 20자 이하여야 합니다");
        }
        if (postUpdateReqDto.getSkills() == null || postUpdateReqDto.getSkills().isEmpty()) {
            throw new CustomApiException("기술스택은 최소1개 선택해 주십시오");
        }
        if (postUpdateReqDto.getComIntro().length() >= 200) {
            throw new CustomApiException("기업소개를 길이가 20자 이하여야 합니다");
        }
        if (postUpdateReqDto.getJobIntro().length() >= 200) {
            throw new CustomApiException("업무소개를 길이가 20자 이하여야 합니다");
        }
        if (postUpdateReqDto.getSkills().split(",").length > 5) {
            throw new CustomApiException("기술스택은 최대 5개 선택해 주십시오");
        }
        postService.공고수정하기(postUpdateReqDto, id, userPS.getCInfoId());

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 수정 성공", null), HttpStatus.CREATED);
        // return "redirect:/company/postDetail/1"; // +id
    }

    @GetMapping("/company/savePostForm")
    public String companySavePostForm(Model model) {
        companyMocLogin();

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.FORBIDDEN);
        }

        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        // if (companyPS.getBossName() == null || companyPS.getBossName().isEmpty()
        // || companyPS.getCyear() == null || companyPS.getLogo() == null ||
        // companyPS.getLogo().isEmpty()
        // || companyPS.getManagerPhone() == null ||
        // companyPS.getManagerPhone().isEmpty()
        // || companyPS.getSize() == null || companyPS.getCyear() == null) {
        // return "redirect:/company/info";
        // }

        model.addAttribute("company", companyPS);
        model.addAttribute("skills", Skill.madeSkills());
        return "company/savePostForm";
    }

    @PostMapping("/company/savePost")
    public String companySavePost(Model model, PostSaveReqDto postSaveReqDto) {
        companyMocLogin();

        User userPS = (User) session.getAttribute("principal");

        if (postSaveReqDto.getTitle() == null ||
                postSaveReqDto.getTitle().isEmpty()) {
            throw new CustomException("title 작성해주세요");
        }
        if (postSaveReqDto.getPay() == null ||
                postSaveReqDto.getPay().isEmpty()) {
            throw new CustomException("연봉 작성해주세요");
        }
        if (postSaveReqDto.getCareer() == null) {
            throw new CustomException("지원 자격 작성해주세요");
        }
        if (postSaveReqDto.getPay() == null ||
                postSaveReqDto.getPay().isEmpty()) {
            throw new CustomException("연봉 작성해주세요");
        }
        if (postSaveReqDto.getCondition() == null ||
                postSaveReqDto.getCondition().isEmpty()) {
            throw new CustomException("근무조건 작성해주세요");
        }
        if (postSaveReqDto.getDeadline() == null ||
                postSaveReqDto.getDeadline().isEmpty()) {
            throw new CustomException("마감시간 작성해주세요");
        }
        if (postSaveReqDto.getStartHour() == null ||
                postSaveReqDto.getStartHour().isEmpty() || (postSaveReqDto.getEndHour() == null ||
                        postSaveReqDto.getEndHour().isEmpty())) {
            throw new CustomException("근무 시간을 작성해주세요");
        }
        if (postSaveReqDto.getJobIntro() == null ||
                postSaveReqDto.getJobIntro().isEmpty()) {
            throw new CustomException("업무소개를 작성해주세요");
        }
        if (postSaveReqDto.getCIntro() == null ||
                postSaveReqDto.getCIntro().isEmpty()) {
            throw new CustomException("기업소개를 작성해주세요");
        }
        if (postSaveReqDto.getTitle().length() >= 20) {
            throw new CustomException("title의 길이가 20자 이하여야 합니다");
        }
        if (postSaveReqDto.getJobIntro().length() >= 200) {
            throw new CustomException("기업소개를 길이가 20자 이하여야 합니다");
        }
        if (postSaveReqDto.getCIntro().length() >= 200) {
            throw new CustomException("업무소개를 길이가 20자 이하여야 합니다");
        }

        // postinsert skillinsert 동시 진행
        int id = postService.공고등록(postSaveReqDto, userPS.getCInfoId());

        return "redirect:/company/postDetail/" + id; // +id
    }

}
