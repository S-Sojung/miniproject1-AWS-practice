package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.miniproject.dto.post.PostResp.PostMainRespDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostRecommendRespDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostTitleRespDto;

@Mapper
public interface PostRespository {
        public List<Post> findAll();

        public Post findById(int id);

        public List<PostTitleRespDto> findAllTitleByCInfoId(int cInfoId);

        public List<PostMainRespDto> findAllWithCInfo();

        public PostRecommendRespDto findAllWithLogoById(int postId);

        public int insert(Post post);
        // @Param("title") String title, @Param("cInfoId") int cInfoId,
        // @Param("career") int career,
        // @Param("pay") String pay, @Param("condition") String condition,
        // @Param("startHour") Time startHour,
        // @Param("endHour") Time endHour, @Param("deadline") Timestamp deadline,
        // @Param("cIntro") String cIntro,
        // @Param("jobIntro") String jobIntro
        // skill은 따로 insert 해줘서 관리해줘야함!

        public int updateById(
                        @Param("id") int id,
                        @Param("title") String title, @Param("cInfoId") int cInfoId,
                        @Param("career") int career,
                        @Param("pay") String pay, @Param("condition") String condition,
                        @Param("startHour") String startHour,
                        @Param("endHour") String endHour, @Param("deadline") Timestamp deadline,
                        @Param("cIntro") String cIntro,
                        @Param("jobIntro") String jobIntro,
                        @Param("createdAt") Timestamp createdAt);

        public int deleteById(int id);

}
