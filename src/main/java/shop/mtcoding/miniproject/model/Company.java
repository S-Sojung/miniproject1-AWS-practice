package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private Integer id;
    private String logo;
    private String name;
    private String number;
    private String address;
    private String managerName;
    private String managerPhone;
    private Integer size;
    private Integer year;
    private Timestamp createdAt;

}
