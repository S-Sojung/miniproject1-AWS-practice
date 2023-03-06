package shop.mtcoding.miniproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContoller {

    @GetMapping("/")
    public String main() {
        return "/siteMain";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/customerService")
    public String customerService() {
        return "/customerService";
    }
}
