package shop.mtcoding.miniproject.dto.post;

import lombok.Getter;
import lombok.Setter;

public class PostResp {

    @Getter
    @Setter
    public static class PostTitleRespDto {
        private int id;
        private String title;
    }
}
