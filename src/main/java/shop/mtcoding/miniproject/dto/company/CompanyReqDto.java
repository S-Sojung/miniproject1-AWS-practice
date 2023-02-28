package shop.mtcoding.miniproject.dto.company;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class CompanyReqDto {

    @Setter
    @Getter
    public static class CompanyUpdateInfoDto {
        private MultipartFile logo;
        private String bossName;
        private String size;
        private String cyear;
        private String managerName;
        private String managerPhone;
        private String address;
        private String password;
    }
}
