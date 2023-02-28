package shop.mtcoding.miniproject.dto.company;

import lombok.Getter;
import lombok.Setter;

public class CompanyReq {

    @Getter
    @Setter
    public static class JoinCompanyReqDto {

        private String name;
        private String aaddress;
        private Integer number;
        private String managerName;
        private String managerEmail;
        private String password;
    }
}
