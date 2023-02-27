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
    private String name;
    private String phone;
    private String address;
    private Timestamp birthday;
    private String skills;

    public Resume() {
    }

    public Resume(ResumeUpdateReqDto resumeUpateReqDto) {
        this.profile = resumeUpateReqDto.getProfile();
        this.title = resumeUpateReqDto.getTitle();
        this.portfolio = resumeUpateReqDto.getPortfolio();
        this.selfIntro = resumeUpateReqDto.getSelfIntro();

    }
}
