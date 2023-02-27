package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;

@Getter
@Setter
public class Skill {
    private Integer id;
    private Integer pInfoId;
    private Integer postId; // 0
    private Integer resumeId; // 0
    private String skills;
    private Timestamp createdAt;

    public Skill() {

    }

    public Skill(ResumeUpdateReqDto resumeUpateReqDto) {
        this.skills = resumeUpateReqDto.getSkills();
    }
}
