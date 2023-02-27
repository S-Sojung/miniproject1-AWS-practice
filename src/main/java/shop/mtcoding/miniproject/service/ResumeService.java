package shop.mtcoding.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpateReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
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

    public void insertNewResume(ResumeUpateReqDto resumeDto, int pInfoId) {
        Resume resume = new Resume(resumeDto);
        resume.setPInfoId(pInfoId);
        int result = resumeRepository.insert(resume);
        if (result != 1) {
            throw new CustomException("이력서 저장에 문제가 생겼네요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 방금 인서트한 게시글 id 가져오기
        System.out.println(resume.getId());
        int resumId = resume.getId();

        personRepository.updateById(resumId, null, null, null, null, null)
    }

}
