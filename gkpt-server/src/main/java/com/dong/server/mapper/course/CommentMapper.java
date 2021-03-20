package com.dong.server.mapper.course;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.server.pojo.course.Comment;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
