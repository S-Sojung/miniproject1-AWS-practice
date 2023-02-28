package shop.mtcoding.miniproject.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.miniproject.dto.post.PostReq.PostSaveReqDto;

@Getter
@Setter
public class Post {
    private Integer id;
    private String title;
    private Integer cInfoId;
    private Integer career;
    private String pay;
    private String condition;
    private String startHour;
    private String endHour;
    private Timestamp deadline;
    private String cIntro;
    private String jobIntro;
    private Timestamp createdAt;

    public Post() {

    };

    public Post(PostSaveReqDto postSaveReqDto, int cInfoId) {

        this.title = postSaveReqDto.getTitle();
        this.cInfoId = cInfoId;
        this.career = postSaveReqDto.getCareer();
        this.pay = postSaveReqDto.getPay();
        this.condition = postSaveReqDto.getCondition();
        this.startHour = postSaveReqDto.getStartHour();
        this.endHour = postSaveReqDto.getEndHour();
        String deadTime = postSaveReqDto.getDeadline().split("T")[0] + " " + postSaveReqDto.getDeadline().split("T")[1];
        this.deadline = Timestamp.valueOf(deadTime);
        this.cIntro = postSaveReqDto.getCIntro();
        this.jobIntro = postSaveReqDto.getJobIntro();
    }
}
