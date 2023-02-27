package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResumeRepository {
        public List<Resume> findAll();

        public Resume findById(int id);

        public int insert(@Param("pInfoId") int pInfoId, @Param("profile") String profile,
                        @Param("title") String title,
                        @Param("portfolio") String portfolio, @Param("selfIntro") String selfIntro);
        // skill은 따로 insert 해줘서 관리해줘야함!

        public int updateById(@Param("id") int id,
                        @Param("pInfoId") int pInfoId, @Param("profile") String profile,
                        @Param("title") String title,
                        @Param("portfolio") String portfolio, @Param("selfIntro") String selfIntro,
                        @Param("createdAt") Timestamp createdAt);

        public int deleteById(int id);

}
