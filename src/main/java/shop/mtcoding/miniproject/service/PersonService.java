package shop.mtcoding.miniproject.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinPersonReqDto joinPersonReqDto) {
        Person person = new Person();
        person.setName(joinPersonReqDto.getName());
        int result = personRepository.insert(person); // joinReqDto(인수)를 매핑

        if (result != 1) {
            throw new CustomException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        int result2 = userRepository.insert(joinPersonReqDto.getEmail(),
                joinPersonReqDto.getPassword(), person.getId(),
                0);

        personRepository.insert(person);
    }

    // public User 로그인(LoginReqPersonDto loginReqPersonDto) {
    // User principal =
    // personRepository.findByEmailAndPassword(loginReqPersonDto.getEmail(),
    // loginReqPersonDto.getPassword());

    // if (principal == null) {
    // throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
    // }
    // return principal;
    // }

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
