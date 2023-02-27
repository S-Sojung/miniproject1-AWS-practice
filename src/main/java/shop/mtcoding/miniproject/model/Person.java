package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;

@Getter
@Setter
public class Person {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Timestamp birthday;
    private Timestamp createdAt;

    public Person() {

    }

    public Person(ResumeUpdateReqDto resumeUpateReqDto) {
        this.name = resumeUpateReqDto.getName();
        this.phone = resumeUpateReqDto.getPhone();
        this.address = resumeUpateReqDto.getAddress();
        this.birthday = resumeUpateReqDto.getBirthday();
    }

}
