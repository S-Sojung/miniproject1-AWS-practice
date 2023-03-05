package shop.mtcoding.miniproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.miniproject.dto.customerService.customerServiceResp.PersonCustomerServiceRespDto;
import shop.mtcoding.miniproject.model.CompanyCustomerServiceRepository;
import shop.mtcoding.miniproject.model.PersonCustomerServiceRepository;

@Controller
public class IndexContoller {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonCustomerServiceRepository personCustomerServiceRepository;

    @Autowired
    private CompanyCustomerServiceRepository companyCustomerServiceRepository;

    @GetMapping("/")
    public String main() {
        return "/siteMain";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/customerService")
    public String customerService(Model model) {

        // 개인, 기업 질문 리스트 가져오기\
        List<PersonCustomerServiceRespDto> personFAQ = (List<PersonCustomerServiceRespDto>) personCustomerServiceRepository.findAll();


        // 모델에 담기
        model.addAttribute("PersonFAQ", model)



        return "/customerService";
    }
}
