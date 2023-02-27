package shop.mtcoding.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.dto.post.PostReq.PostSaveReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.model.Post;
import shop.mtcoding.miniproject.model.PostRespository;
import shop.mtcoding.miniproject.model.SkillRepository;

@Transactional // 여기 붙이면 모든 메서드에 다 붙음
@Service
public class PostService {
    @Autowired
    private PostRespository postRespository;
    @Autowired
    private SkillRepository skillRepository;

    public int 공고등록(PostSaveReqDto postSaveReqDto, int cInfoId) {
        Post post = new Post(postSaveReqDto, cInfoId);
        int result = postRespository.insert(post);
        if (result != 1) {
            throw new CustomApiException("공고 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String[] st = postSaveReqDto.getSkills();
        String skills = "";
        for (String string : st) {
            if (!skills.equals(""))
                skills += ",";
            skills += string;
        }
        int result1 = skillRepository.insert(0, post.getId(), 0, skills);
        if (result1 != 1) {
            throw new CustomApiException("공고 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return post.getId();
    }
}
