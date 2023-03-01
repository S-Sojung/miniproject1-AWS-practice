package shop.mtcoding.miniproject.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SkillFilterRepository {

        public List<SkillFilter> findAll();

        public SkillFilter findById(int id);

        public int insert(@Param("skill") String skill, @Param("postId") int postId,
                        @Param("resumeId") int resumeId);

        public int updateById(@Param("id") int id,
                        @Param("skill") String skill,
                        @Param("postId") int postId,
                        @Param("resumeId") int resumeId);

        public int deleteById(int id);
}
