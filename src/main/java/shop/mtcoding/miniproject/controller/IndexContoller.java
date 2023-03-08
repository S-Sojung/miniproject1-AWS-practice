package shop.mtcoding.miniproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.miniproject.model.CompanyCustomerService;
import shop.mtcoding.miniproject.model.CompanyCustomerServiceRepository;
import shop.mtcoding.miniproject.model.PersonCustomerService;
import shop.mtcoding.miniproject.model.PersonCustomerServiceRepository;
import shop.mtcoding.miniproject.model.User;

@Controller
public class IndexContoller {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private PersonCustomerServiceRepository personCustomerServiceRepository;

    @Autowired
    private CompanyCustomerServiceRepository companyCustomerServiceRepository;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String main() {
        User principal = (User) redisTemplate.opsForValue().get("principal");

        if (principal != null) {
            session.setAttribute("principal", principal);
            if (principal.getPInfoId() != 0) {
                return "redirect:/person";
            } else {
                return "redirect:/company";
            }
        }

        return "/siteMain";
    }

    @GetMapping("/logout")
    public String logout() {
        redisTemplate.opsForValue().set("principal", null);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/customerService")
    public String customerService(Model model) {

        // 개인, 기업 질문 리스트 가져오기
        List<PersonCustomerService> personFAQ = (List<PersonCustomerService>) personCustomerServiceRepository.findAll();
        List<CompanyCustomerService> companyFAQ = (List<CompanyCustomerService>) companyCustomerServiceRepository
                .findAll();

        // 모델에 담기
        model.addAttribute("personFAQ", personFAQ);
        model.addAttribute("companyFAQ", companyFAQ);
        return "/customerService";
    }
}
