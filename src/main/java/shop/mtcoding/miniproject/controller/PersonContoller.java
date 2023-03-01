package shop.mtcoding.miniproject.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shop.mtcoding.miniproject.dto.ResponseDto;
import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.LoginPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReqDto.PersonUpdateDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostMainRespDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.PostRespository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.model.UserRepository;
import shop.mtcoding.miniproject.service.PersonService;
import shop.mtcoding.miniproject.service.ResumeService;

@Controller
public class PersonContoller {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRespository postRepository;

    @Autowired
    private SkillRepository skillRepository;

    public void personMocLogin() {
        User user = new User();
        user.setId(1);
        user.setCInfoId(0);
        user.setPInfoId(1);
        user.setEmail("ssar@nate.com");
        user.setPassword("1234");
        session.setAttribute("principal", user);
    }

    @GetMapping("/personLoginForm")
    public String personLoginForm() {
        return "person/loginForm";
    }

    @PostMapping("/personLogin")
    public String personLogin(LoginPersonReqDto loginPersonReqDto) {

        if (loginPersonReqDto.getEmail() == null ||
                loginPersonReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 작성해주세요");
        }

        if (loginPersonReqDto.getPassword() == null ||
                loginPersonReqDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 작성해주세요");
        }

        User principal = userRepository.findByEmailAndPassword(loginPersonReqDto.getEmail(),
                loginPersonReqDto.getPassword());
        if (principal == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }

        session.setAttribute("principal", principal);

        return "redirect:/person/main";
    }

    @GetMapping("/personJoinForm1")
    public String personJoinForm1() {
        return "person/joinForm1";
    }

    @PostMapping("/personJoin")
    public String join(JoinPersonReqDto joinPersonReqDto, RedirectAttributes redirectAttributes) {

        System.out.println("테스트 : " + joinPersonReqDto.getName());
        System.out.println("테스트 : " + joinPersonReqDto.getPassword());

        if (joinPersonReqDto.getName() == null ||
                joinPersonReqDto.getName().isEmpty()) {
            throw new CustomException("이름을 작성해주세요");
        }
        if (joinPersonReqDto.getPassword() == null ||
                joinPersonReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }
        if (joinPersonReqDto.getEmail() == null ||
                joinPersonReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 작성해주세요");
        }
        int id = personService.join(joinPersonReqDto);
        redirectAttributes.addAttribute("pInfoId", id);
        // Person 인서트를 이름만!
        // Person 인서트한 id 값을 유저에게 인서트하기

        return "redirect:/personJoinForm2";
    }

    @GetMapping("/personJoinForm2")
    public String personJoinForm2(Model model, int pInfoId) {
        model.addAttribute("pInfoId", pInfoId);
        model.addAttribute("skills", Skill.madeSkills());
        return "person/joinForm2";
    }

    @PostMapping("/personJoin2")
    public String join(String[] skills, int pInfoId) {

        String checkedSkills = "";
        for (int i = 0; i < skills.length; i++) {
            if (checkedSkills != "") {
                checkedSkills += ",";
            }
            checkedSkills += skills[i];
            // System.out.println(checkedSkills); 테스트
        }
        System.out.println("테스트: " + pInfoId);

        return "redirect:/personLoginForm";
    }

    @GetMapping({ "/person/main", "/person" })
    public String personMain(Model model) {
        personMocLogin();
        // 회사로고, 회사이름, 공고이름, 회사 주소, D-day
        // cInfo : 회사로고, 회사이름, 회사주소
        // 공고 정보 : 공고이름, 디데이
        List<PostMainRespDto> postList = (List<PostMainRespDto>) postRepository.findAllWithCInfo();

        model.addAttribute("mainPosts", postList);
        model.addAttribute("size", postList.size());
        return "person/main";
    }

    @GetMapping("/person/detail/{id}")
    public String personDetail(@PathVariable int id) {

        return "person/detail";
    }

    @GetMapping("/person/recommend")
    public String personRecommend() {
        return "person/recommend";
    }

    @GetMapping("/person/info")
    public String personInfo(Model model) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        Person PersonPS = personRepository.findById(principal.getPInfoId());

        model.addAttribute("person", PersonPS);

        Skill pSkill = skillRepository.findByPInfoId(principal.getPInfoId());
        String pSkills = pSkill.getSkills();

        String[] pSkillArr = pSkills.split(",");
        model.addAttribute("pSkillArr", pSkillArr);

        return "person/info";
    }

    @GetMapping("/person/updateInfoForm")
    public String personUpdateInfoForm(Model model) {
        personMocLogin();

        User principal = (User) session.getAttribute("principal");
        Person PersonPS = personRepository.findById(principal.getPInfoId());

        model.addAttribute("person", PersonPS);

        Skill pSkill = skillRepository.findByPInfoId(principal.getPInfoId());
        String pSkills = pSkill.getSkills();

        model.addAttribute("pSkills", pSkills);
        model.addAttribute("skills", Skill.madeSkills());
        return "person/updateInfoForm";
    }

    @PutMapping("/person/updateInfo")
    public ResponseEntity<?> personUpdate(@RequestBody PersonUpdateDto personUpdateDto) {
        // 필수인지 헷갈림
        User principal = (User) session.getAttribute("principal");
        Person PersonPS = personRepository.findById(principal.getPInfoId());

        if (PersonPS == null) {
            throw new CustomApiException("존재하지 않는 유저입니다");
        }
        //
        if (personUpdateDto.getName() == null || personUpdateDto.getName().isEmpty()) {
            throw new CustomApiException("이름을 확인해주세요");
        }
        if (personUpdateDto.getPhone() == null || personUpdateDto.getPhone().isEmpty()) {
            throw new CustomApiException("전화 번호를 확인해주세요");
        }
        if (personUpdateDto.getEmail() == null || personUpdateDto.getEmail().isEmpty()) {
            throw new CustomApiException("이메일을 확인해주세요");
        }
        if (personUpdateDto.getAddress() == null || personUpdateDto.getAddress().isEmpty()) {
            throw new CustomApiException("주소를 확인해주세요");
        }

        personService.update(personUpdateDto, principal.getPInfoId());

        return new ResponseEntity<>(new ResponseDto<>(1, "회원 정보 수정 완료", null), HttpStatus.OK);
    }

    @GetMapping("/person/history")
    public String personHistory() {
        return "person/history";
    }

    @GetMapping("/person/resumes")
    public String personResumes(Model model) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        int pInfoId = principal.getPInfoId();
        List<Resume> resumeAll = resumeRepository.findAll();
        model.addAttribute("resume", resumeAll);
        Person personPS = personRepository.findById(pInfoId);
        model.addAttribute("personPS", personPS);
        return "person/resumes";
    }

    @DeleteMapping("/person/resumes/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable int id) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        int result = resumeRepository.deleteById(id);
        if (result != 1) {
            throw new CustomApiException("이력서 삭제 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 삭제 성공", null), HttpStatus.OK);
    }

    @GetMapping("/person/saveResumeForm")
    public String personSaveResumeForm() {
        return "person/saveResumeForm";
    }

    @GetMapping("/person/resumeDetail/{id}")
    public String personResumeDetail(@PathVariable int id, Model model) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Resume resumePS = resumeRepository.findById(id);
        if (resumePS == null) {
            throw new CustomApiException("없는 게시글을 수정할 수 없습니다");
        }
        Person personPS = personRepository.findById(resumePS.getPInfoId());
        Skill skillPS = skillRepository.findByPInfoId(resumePS.getPInfoId());
        model.addAttribute("resumeDetail", resumePS);
        model.addAttribute("personDetail", personPS);
        model.addAttribute("skillDetail", skillPS.getSkills().split(","));
        return "person/resumeDetail";
    }

    @GetMapping("/person/scrap")
    public String personScrap() {
        return "person/scrap";
    }

    @PostMapping("/person/resumes")
    public String personInsertResumeForm(ResumeUpdateReqDto resumeUpdateReqDto, Model model) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        int pInfoId = principal.getPInfoId();
        if (resumeUpdateReqDto.getProfile().isEmpty()) {
            throw new CustomException("프로필 사진을 업로드 해주세요");
        }
        if (resumeUpdateReqDto.getTitle() == null || resumeUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomException("제목를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPortfolio() == null || resumeUpdateReqDto.getPortfolio().isEmpty()) {
            throw new CustomException("포트폴리오 주소를 작성해주세요");
        }
        if (resumeUpdateReqDto.getSelfIntro() == null || resumeUpdateReqDto.getSelfIntro().isEmpty()) {
            throw new CustomException("자기소개서를 작성해주세요");
        }
        if (resumeUpdateReqDto.getName() == null || resumeUpdateReqDto.getName().isEmpty()) {
            throw new CustomException("이름를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPhone() == null || resumeUpdateReqDto.getPhone().isEmpty()) {
            throw new CustomException("휴대폰 번호를 작성해주세요");
        }
        if (resumeUpdateReqDto.getBirthday() == null || resumeUpdateReqDto.getBirthday().isEmpty()) {
            throw new CustomException("생년월일을 작성해주세요");
        }
        if (resumeUpdateReqDto.getSkills() == null || resumeUpdateReqDto.getSkills().isEmpty()) {
            throw new CustomException("기술스택을 선택해주세요");
        }
        resumeService.insertNewResume(pInfoId, resumeUpdateReqDto);

        return "redirect:/person/resumes";
    }

    @GetMapping("/person/updateResume/{id}")
    public String getUpdateResumeForm(@PathVariable int id, Model model) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Resume resumePS = resumeRepository.findById(id);
        Person personPS = personRepository.findById(resumePS.getPInfoId());
        Skill skillPS = skillRepository.findByPInfoId(resumePS.getPInfoId());
        model.addAttribute("resumePS", resumePS);
        model.addAttribute("personPS", personPS);
        model.addAttribute("skillPS", skillPS.getSkills());
        model.addAttribute("skills", Skill.madeSkills());
        return "person/updateResumeForm";
    }

    @PostMapping("/person/updateResume/{id}")
    public String UpdateResumeForm(@PathVariable int id, ResumeUpdateReqDto resumeUpdateReqDto, Model model) {
        personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (resumeUpdateReqDto.getProfile().isEmpty()) {
            throw new CustomException("프로필 사진을 업로드 해주세요");
        }
        if (resumeUpdateReqDto.getTitle() == null || resumeUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomException("제목를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPortfolio() == null || resumeUpdateReqDto.getPortfolio().isEmpty()) {
            throw new CustomException("포트폴리오 주소를 작성해주세요");
        }
        if (resumeUpdateReqDto.getSelfIntro() == null || resumeUpdateReqDto.getSelfIntro().isEmpty()) {
            throw new CustomException("자기소개서를 작성해주세요");
        }
        if (resumeUpdateReqDto.getName() == null || resumeUpdateReqDto.getName().isEmpty()) {
            throw new CustomException("이름를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPhone() == null || resumeUpdateReqDto.getPhone().isEmpty()) {
            throw new CustomException("휴대폰 번호를 작성해주세요");
        }
        if (resumeUpdateReqDto.getBirthday() == null || resumeUpdateReqDto.getBirthday().isEmpty()) {
            throw new CustomException("생년월일을 작성해주세요");
        }
        if (resumeUpdateReqDto.getSkills() == null || resumeUpdateReqDto.getSkills().isEmpty()) {
            throw new CustomException("기술스택을 선택해주세요");
        }
        int pInfoId = principal.getPInfoId();
        resumeService.updateById(id, pInfoId, resumeUpdateReqDto);
        Resume resumePS = resumeRepository.findById(id);
        model.addAttribute("resumePS", resumePS);

        return "redirect:/person/resumeDetail/" + id;

    }

}
