package shop.mtcoding.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillRepository;

@Transactional
@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    SkillRepository skillRepository;

    public void insertNewResume(Resume resume) {
        int result1 = resumeRepository.insert(resume);
        if (result1 != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 방금 인서트한 게시글 id 가져오기
        System.out.println(resume.getId());

        // findByPInfoId => person 만들어서 (유저의 생성일자는 같아야 함)
        Person person = personRepository.findById(resume.getPInfoId());
        person.getCreatedAt();
        int result2 = personRepository.updateById(resume.getPInfoId(), resume.getName(), resume.getPhone(),
                resume.getAddress(),
                resume.getBirthday(), person.getCreatedAt());
        if (result2 != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Skill skill = skillRepository.findByPInfoId(resume.getPInfoId());
        int result3 = skillRepository.updateById(skill.getId(), resume.getPInfoId(), 0, 0, resume.getSkills(),
                skill.getCreatedAt());
        if (result3 != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
