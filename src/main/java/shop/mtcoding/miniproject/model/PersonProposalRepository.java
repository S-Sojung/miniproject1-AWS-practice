package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.miniproject.dto.personProposal.PersonProposalResp.PersonProposalListRespDto;

@Mapper
public interface PersonProposalRepository {
        public List<PersonProposal> findAll();

        public PersonProposal findById(int id);

        public List<PersonProposalListRespDto> findAllWithPostAndCInfoByPInfoId(int pInfoId);

        public int insert(@Param("pInfoId") int pInfoId,
                        @Param("postId") int postId,
                        @Param("resumeId") int resumeId,
                        @Param("status") int status, // 0은 대기중, 1은 합격, 2는 불합격
                        @Param("createdAt") Timestamp createdAt);

        public int updateById(@Param("id") int id,
                        @Param("pInfoId") int pInfoId,
                        @Param("postId") int postId,
                        @Param("resumeId") int resumeId,
                        @Param("status") int status,
                        @Param("createdAt") Timestamp createdAt);

        public int deleteById(int id);

}
