package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;

@Getter
@Setter
public class Resume {
    private Integer id;
    private Integer pInfoId;
    private String profile;
    private String title;
    private String portfolio;
    private String selfIntro;
    private Timestamp createdAt;

    public Resume() {
    }

    public Resume(ResumeUpdateReqDto resumeUpdateReqDto) {
        this.title = resumeUpdateReqDto.getTitle();
        this.portfolio = resumeUpdateReqDto.getPortfolio();
        this.selfIntro = resumeUpdateReqDto.getSelfIntro();
    }
}
