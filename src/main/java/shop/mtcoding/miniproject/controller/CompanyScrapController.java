package shop.mtcoding.miniproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import shop.mtcoding.miniproject.dto.ResponseDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.service.CompanyScrapService;

@Controller
public class CompanyScrapController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CompanyScrapService companyScrapService;

    @DeleteMapping("/company/scrap/{id}")
    public ResponseEntity<?> scrapDelete(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        companyScrapService.delete(id, principal.getCInfoId());
        return new ResponseEntity<>(new ResponseDto<>(1, "스크랩 취소", null), HttpStatus.OK);
    }

    @PutMapping("/company/scrap/{id}")
    public ResponseEntity<?> scrapInsert(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        companyScrapService.insert(id, principal.getCInfoId());
        return new ResponseEntity<>(new ResponseDto<>(1, "스크랩 완료", null), HttpStatus.OK);
    }

}
