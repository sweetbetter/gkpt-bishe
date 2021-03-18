package com.dong.server.controller.course;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.server.controller.course.vm.TeacherQueryVM;
import com.dong.server.pojo.ResponseBean;
import com.dong.server.pojo.course.Teacher;
import com.dong.server.service.IFileService;
import com.dong.server.service.course.ITeacherService;
import com.dong.server.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author caojingdong
 * @since 2021-03-09
 */
@Api("讲师管理")
@RestController
@RequestMapping("/course/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;


    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll(){
        return R.ok().data("list",teacherService.list()).message("查询成功！");
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("讲师id") @PathVariable String id){
        boolean result2= teacherService.removeAvatar(id);
        boolean result= teacherService.removeById(id);//失败返回0
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("讲师不存在");
        }
    }

//    批量删除
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("remove-batch")
    public R removeBatch(@ApiParam(value = "讲师id列表",required = true)
                             @RequestBody List<String> idList){
        boolean result= teacherService.removeByIds(idList);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("讲师不存在");
        }
    }

    @ApiOperation("分页讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam(value = "查询参数对象", required = false)  TeacherQueryVM teacherQueryVM){
        Page<Teacher> pageParam = new Page<>(page, limit);
//        IPage<Teacher> pageModel = teacherService.page(pageParam);
        IPage<Teacher> pageModel = teacherService.selectTeacherWithPage(teacherQueryVM,pageParam);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }


    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "讲师ID", required = true) @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher != null){
            return R.ok().data("item", teacher);
        }else{
            return R.error().message("数据不存在");
        }
    }

}
