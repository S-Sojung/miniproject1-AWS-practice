package shop.mtcoding.miniproject.dto.personProposal;

import lombok.Getter;
import lombok.Setter;

public class PersonProposalReq {
    @Getter
    @Setter
    public static class CompanyProposalStatusReqDto {
        private int statusCode;
    }
}
