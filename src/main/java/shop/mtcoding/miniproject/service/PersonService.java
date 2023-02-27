package shop.mtcoding.miniproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.dto.person.PersonReqDto.PersonUpdateDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillRepository;

@Service
public class PersonService {

    @Autowired
    private SkillRepository skillrepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public void update(PersonUpdateDto personUpdateDto, int pInfoId) {

        Person personPS = personRepository.findById(pInfoId);

        int result = personRepository.updateById(pInfoId, personUpdateDto.getName(), personUpdateDto.getPhone(),
                personUpdateDto.getAddress(), null, personPS.getCreatedAt());

        if (result != 1) {
            throw new CustomApiException("정보 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Skill skillPS = skillrepository.findByPInfoId(pInfoId);

        if (skillPS == null) {
            throw new CustomApiException("정보를 찾을 수 없습니다");
        }

        int result2 = skillrepository.updateById(skillPS.getId(), pInfoId, 0, 0, personUpdateDto.getSkills(),
                skillPS.getCreatedAt());

        if (result2 != 1) {
            throw new CustomApiException("정보 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
