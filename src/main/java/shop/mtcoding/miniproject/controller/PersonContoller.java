package shop.mtcoding.miniproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.service.ResumeService;

@Controller
public class PersonContoller {
    @Autowired
    ResumeService resumeService;

    @Autowired
    private HttpSession session;

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
    public String personInfo() {
        return "person/info";
    }

    @GetMapping("/person/updateInfoForm")
    public String personUpdateInfoForm() {
        return "person/updateInfoForm";
    }

    @GetMapping("/person/history")
    public String personHistory() {
        return "person/history";
    }

    @GetMapping("/person/resumes")
    public String personResumes() {
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
    public String personInsertResumeForm(Resume resume) {
        User user = (User) session.getAttribute("principal");
        int pInfoId = user.getPInfoId();
        resume.setPInfoId(pInfoId);
        resumeService.insertNewResume(resume);
        return "redirect:/person/resumes";
    }
}
