package shop.mtcoding.miniproject.dto.Resume;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

public class ResumeReq {
    @Getter
    public static class ResumeUpdateReqDto {
        private MultipartFile profile;
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
