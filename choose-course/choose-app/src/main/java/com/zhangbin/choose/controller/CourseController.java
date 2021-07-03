package com.zhangbin.choose.controller;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Course;
import com.zhangbin.choose.service.CourseService;
import com.zhangbin.choose.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "CourseController")
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @Autowired
    private UserService userService;

    /**
     * 查询全部
     *
     * @param pageNum 页数
     * @return 查询全部页面
     */
    @ApiOperation(value = "Course分页查询全部", notes = "分页查询全部Course方法详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer")
    })
    @GetMapping("/page/{pageNum}")
    public String findAll(@PathVariable int pageNum, Model model) {
        model.addAttribute("page", service.findPageAll(pageNum));
        return "/course/findAll";
    }

    /**
     * 删除
     *
     * @return 查询全部页面
     */
    @ApiOperation(value = "Course删除", notes = "删除Course方法详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "删除的CourseID", required = true, dataType = "Integer")
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/course/page/1";
    }

    /**
     * 根据ID查询
     *
     * @param id 课程ID
     */
    @ApiOperation(value = "Course根据ID查询", notes = "根据ID查询Course方法详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "查询的CourseID", required = true, dataType = "Integer")
    })
    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("teachers", userService.findByType(1));
        model.addAttribute("course", service.findById(id));
        return "/course/add";
    }

    /**
     * 根据ID查询 返回Json数据
     *
     * @param id 课程ID
     */
    @ApiOperation(value = "Course根据页号与学生ID查询", notes = "根据页号与学生ID查询课程详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "id", value = "学生ID", required = true, dataType = "String")
    })
    @GetMapping("/json/{pageNum}/{id}")
    @ResponseBody
    public List<Course> findById(@PathVariable String id, @PathVariable int pageNum) {
        List<Course> list = service.findByStudent(pageNum, id).getList();
        System.out.println(list);
        return list;
    }

    /**
     * 跳转到添加页面
     *
     * @return 添加页面
     */
    @ApiOperation(value = "跳转到添加页面", notes = "跳转到添加页面方法详情", tags = {"CourseController"})
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("teachers", userService.findByType(1));
        return "/course/add";
    }

    /**
     * 添加课程操作
     *
     * @param course 课程信息
     * @return 查询全部页面
     */
    @ApiOperation(value = "Course添加方法", notes = "Course添加方法详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "course", value = "课程对象", required = true, dataType = "Course")
    })
    @PutMapping
    public String add(Course course, Model model) {
        service.update(course);
        return "redirect:/course/page/1";
    }

    /**
     * 修改信息
     *
     * @return 查询全部页面
     */
    @ApiOperation(value = "Course修改方法", notes = "Course修改方法详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "course", value = "课程对象", required = true, dataType = "Course")
    })
    @PostMapping
    public String add(Course course) {
        service.add(course);
        return "redirect:/course/page/1";
    }

    /**
     * 通过学生ID查询学生未选课程
     *
     * @param id 学生ID
     * @return 课程Info
     */
    @ApiOperation(value = "通过学生ID查询学生未选课程", notes = "通过学生ID查询学生未选课程方法详情", tags = {"CourseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "学生ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer")
    })
    @GetMapping("/{id}/{pageNum}")
    public String findCourseStudentNot(@PathVariable Integer pageNum, @PathVariable String id, Model model) {
        model.addAttribute("page", service.findCourseStudentNot(pageNum, id));
        return "/course/findAll";
    }

}
