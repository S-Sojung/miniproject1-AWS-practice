package shop.mtcoding.miniproject.dto.personProposal;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class PersonProposalResp {
    @Getter
    @Setter
    public static class PersonProposalListRespDto {
        private int id;
        private int pInfoId;
        private int postId;
        private int resumeId;
        private int status;
        private Timestamp createdAt;
        private String title;
        private Timestamp deadline;
        private String name;
    }
}
