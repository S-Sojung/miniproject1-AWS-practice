package shop.mtcoding.miniproject.dto.Resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class ResumeReq {

    @Setter
    @Getter
    public static class ResumeUpdateReqDto {
        private String profile;
        private String title;
        private String portfolio;
        private String selfIntro;
        private String name;
        private String phone;
        private String address;
        private Timestamp birthday;
        private String skills;
    }
}
