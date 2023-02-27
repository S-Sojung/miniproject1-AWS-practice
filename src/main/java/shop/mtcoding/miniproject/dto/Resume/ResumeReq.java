package shop.mtcoding.miniproject.dto.Resume;

import lombok.Getter;
import lombok.Setter;

public class ResumeReq {

    @Setter
    @Getter
    public static class ResumeUpateReqDto {
        private String profile;
        private String title;
        private String portfolio;
        private String selfIntro;
    }
}
