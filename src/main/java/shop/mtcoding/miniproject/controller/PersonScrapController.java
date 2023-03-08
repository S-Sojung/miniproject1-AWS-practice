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
import shop.mtcoding.miniproject.model.PersonScrapRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.service.PersonScrapService;

@Controller
public class PersonScrapController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonScrapRepository personScrapRepository;

    @Autowired
    private PersonScrapService personScrapService;

    public void personMocLogin() {
        User user = new User();
        user.setId(1);
        user.setCInfoId(0);
        user.setPInfoId(1);
        user.setEmail("ssar@nate.com");
        user.setPassword("1234");
        session.setAttribute("principal", user);
    }

    @PutMapping("/person/scrap/{id}")
    public ResponseEntity<?> ScrapInsert(@PathVariable int id) {
        // personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        personScrapService.insert(id, principal.getPInfoId());
        return new ResponseEntity<>(new ResponseDto<>(1, "스크랩 완료", null), HttpStatus.OK);
    }

    @DeleteMapping("/person/scrap/{id}")
    public ResponseEntity<?> ScrapDelete(@PathVariable int id) {
        // personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        personScrapService.delete(id, principal.getPInfoId());
        return new ResponseEntity<>(new ResponseDto<>(1, "스크랩 취소 완료", null), HttpStatus.OK);
    }

}
