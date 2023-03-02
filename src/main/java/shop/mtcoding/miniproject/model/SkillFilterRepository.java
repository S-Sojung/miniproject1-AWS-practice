package shop.mtcoding.miniproject.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.miniproject.dto.skill.SkillResDto.SkillFilterCountResDto;

@Mapper
public interface SkillFilterRepository {

        public List<SkillFilter> findAll();

        public SkillFilter findById(int id);

        public List<SkillFilter> findSkillName(String skill);

        public List<SkillFilterCountResDto> findAllOrderByCount();

        public int insert(@Param("skill") String skill, @Param("postId") int postId,
                        @Param("resumeId") int resumeId);

        public int updateById(@Param("id") int id,
                        @Param("skill") String skill,
                        @Param("postId") int postId,
                        @Param("resumeId") int resumeId);

        public int deleteById(int id);
}
