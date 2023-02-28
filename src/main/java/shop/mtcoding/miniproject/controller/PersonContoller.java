package shop.mtcoding.miniproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.miniproject.dto.ResponseDto;
import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReqDto.PersonUpdateDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillRepository;
import shop.mtcoding.miniproject.model.User;
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

    @GetMapping("/personJoinForm1")
    public String personJoinForm1() {
        return "person/joinForm1";
    }

    @GetMapping("/personJoinForm2")
    public String personJoinForm2() {
        // jsp에서 받은 값을 여기에 들고와서 넘겨줘야함
        return "person/joinForm2";
    }

    @GetMapping({ "/person/main", "/person" })
    public String personMain() {
        personMocLogin();

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

        String[] pSkillArr = pSkills.split(",");
        model.addAttribute("pSkillArr", pSkillArr);

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
        if (personUpdateDto.getPassword() == null || personUpdateDto.getPassword().isEmpty()) {
            throw new CustomApiException("비밀번호를 확인해주세요");
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
        List<Resume> resumeAll = resumeRepository.findAll();
        model.addAttribute("resume", resumeAll);
        return "person/resumes";
    }

    @GetMapping("/person/saveResumeForm")
    public String personSaveResumeForm() {
        return "person/saveResumeForm";
    }

    @GetMapping("/person/resumeDetail")
    public String personResumeDetail() {
        return "person/resumeDetail";
    }

    @GetMapping("/person/scrap")
    public String personScrap() {
        return "person/scrap";
    }

    @PostMapping("/person/resumes")
    public String personInsertResumeForm(ResumeUpdateReqDto resumeUpdateReqDto) {
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
        resumeService.insertNewResume(pInfoId, resumeUpdateReqDto);

        return "redirect:/person/resumes";
    }
}
