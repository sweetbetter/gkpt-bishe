package com.dong.server.service.course.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.mapper.course.VideoMapper;
import com.dong.server.pojo.course.Video;
import com.dong.server.service.course.IVideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
