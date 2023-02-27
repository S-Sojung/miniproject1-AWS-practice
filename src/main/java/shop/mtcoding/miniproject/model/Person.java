package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Person {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Timestamp birthday;
    private Timestamp createdAt;

}
