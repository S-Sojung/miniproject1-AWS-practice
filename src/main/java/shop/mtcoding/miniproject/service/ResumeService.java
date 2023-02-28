package shop.mtcoding.miniproject.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillRepository;
import shop.mtcoding.miniproject.util.CvTimestamp;
import shop.mtcoding.miniproject.util.PathUtil;

@Transactional
@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    SkillRepository skillRepository;

    public void insertNewResume(int pInfoId, ResumeUpdateReqDto resumeUpdateReqDto) {

        String uuidImageName = PathUtil.writeImageFile(resumeUpdateReqDto.getProfile());
        Timestamp birthday = CvTimestamp.convertStringToTimestamp(resumeUpdateReqDto.getBirthday());

        Resume resume = new Resume(resumeUpdateReqDto);
        resume.setProfile(uuidImageName);
        resume.setPInfoId(pInfoId);
        Person person = new Person(resumeUpdateReqDto);
        person.setBirthday(birthday);
        Skill skill = new Skill(resumeUpdateReqDto);

        int result1 = resumeRepository.insert(resume);
        if (result1 != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Person personPS = personRepository.findById(resume.getPInfoId());
        int result2 = personRepository.updateById(resume.getPInfoId(), person.getName(), person.getPhone(),
                person.getAddress(),
                person.getBirthday(), personPS.getCreatedAt());
        if (result2 != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Skill skillPS = skillRepository.findByPInfoId(resume.getPInfoId());

        int result3 = skillRepository.updateById(skillPS.getId(), resume.getPInfoId(), 0, 0, skill.getSkills(),
                skillPS.getCreatedAt());
        if (result3 != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
