package com.zhangbin.choose.controller;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Course;
import com.zhangbin.choose.pojo.StudentCourse;
import com.zhangbin.choose.pojo.User;
import com.zhangbin.choose.pojo.UserInfo;
import com.zhangbin.choose.service.StudentCourseService;
import com.zhangbin.choose.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "StudentCourseController")
@Controller
@RequestMapping("/student-course")
public class StudentCourseController {

    @Autowired
    private StudentCourseService service;

    @Autowired
    private UserService userService;

    /**
     * 删除课程信息
     *
     * @param scId 学生选课ID
     */
    @ApiOperation(value = "通过ID删除学生课程方法", notes = "通过ID删除学生课程方法", tags = {"StudentCourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "scId", value = "学生课程ID", required = true, dataType = "Integer")
    })
    @DeleteMapping("/{scId}")
    public String delete(@PathVariable Integer scId, HttpSession session) {
        service.delete(scId);
        return "redirect:/student-course/1/" + ((UserInfo) session.getAttribute("user")).getUser().getId();
    }


    /**
     * 通过学生ID查找课程信息
     *
     * @param id      学生ID
     * @param pageNum 页数
     * @return 查询全部页面
     */
    @ApiOperation(value = "分页查找学生选课信息", notes = "分页查找学生选课信息方法详情", tags = {"StudentCourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "id", value = "学生ID", required = true, dataType = "String"),
    })
    @GetMapping("/{pageNum}/{id}")
    public String findByStu(@PathVariable String id, @PathVariable int pageNum, Model model) {
        PageInfo<StudentCourse> page = service.findByStudentId(id, pageNum);
        model.addAttribute("page", page);
        return "/student-course/findAll";
    }

    /**
     * 选课操作
     */
    @ApiOperation(value = "选课方法", notes = "选课方法详情", tags = {"StudentCourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "json", name = "sc", value = "学生选课信息", required = true, dataType = "StudentCourse")
    })
    @PostMapping("/choose")
    @ResponseBody
    public String choose(@RequestBody StudentCourse sc) {
        System.out.println(sc);
        service.add(sc);
        return "true";
    }


}
