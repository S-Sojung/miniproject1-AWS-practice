package shop.mtcoding.miniproject.dto.post;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class PostResp {

    @Getter
    @Setter
    public static class PostTitleRespDto {
        private Integer id;
        private String title;
        private Date deadline;
    }

    @Getter
    @Setter
    public static class PostMainRespDto {
        private Integer postId;
        private String title;
        private Integer cInfoId;
        private Timestamp deadline;
        private String logo;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    public static class PostRecommendRespDto {
        private int postId;
        private String title;
        private Timestamp deadline;
        private String logo;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    public static class PostRecommendStringRespDto {
        private int postId;
        private String title;
        private int deadline;
        private String logo;
        private String name;
        private String address;
    }

}
