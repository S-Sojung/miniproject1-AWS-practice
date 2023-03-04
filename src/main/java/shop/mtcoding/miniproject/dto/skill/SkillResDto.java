package shop.mtcoding.miniproject.dto.skill;

import lombok.Getter;
import lombok.Setter;

public class SkillResDto {
    @Getter
    @Setter
    public static class SkillFilterCountResDto {
        private int postId;
        private int count;
    }

}