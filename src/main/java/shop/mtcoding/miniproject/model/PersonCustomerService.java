package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCustomerService {
    private int id;
    private String question;
    private String answer;
    private Timestamp createdAt;
}
