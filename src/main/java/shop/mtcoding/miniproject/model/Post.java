package shop.mtcoding.miniproject.model;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private Integer id;
    private String title;
    private Integer cInfoId;
    private Integer career;
    private String pay;
    private String condition;
    private Time startHour;
    private Time endHour;
    private Timestamp deadline;
    private String cIntro;
    private String jobIntro;
    private Timestamp createdAt;

}
