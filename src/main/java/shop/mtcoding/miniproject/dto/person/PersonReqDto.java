package shop.mtcoding.miniproject.dto.person;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class PersonReqDto {

    @Getter
    @Setter
    public static class PersonUpdateDto {
        private String name;
        private String birthday;
        private String phone;
        private String email;
        private String address;
        private String password;
        private String skills;
        private String originPassword;
    }

}
