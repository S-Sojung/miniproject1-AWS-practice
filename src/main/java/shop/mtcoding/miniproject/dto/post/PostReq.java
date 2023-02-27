package shop.mtcoding.miniproject.dto.post;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class PostReq {
    @Getter
    @Setter
    public static class PostSaveReqDto {
        private String title;
        private int career;
        private String pay;
        private String condition;
        private String startHour;
        private String endHour;
        private String deadline;
        private String cIntro;
        private String jobIntro;
        private String[] skills;
    }
}
