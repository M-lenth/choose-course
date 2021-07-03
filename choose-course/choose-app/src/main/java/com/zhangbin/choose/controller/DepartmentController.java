package com.zhangbin.choose.controller;

import com.zhangbin.choose.pojo.Department;
import com.zhangbin.choose.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(value = "DepartmentController")
@Controller
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    /**
     * 分页查询
     *
     * @param pageNum 页数
     * @return 学院显示页
     */
    @ApiOperation(value = "Department分页查询全部", notes = "分页查询全部Department方法详情", tags = {"DepartmentController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer")
    })
    @GetMapping("/page/{pageNum}")
    public String findAll(@PathVariable int pageNum, Model model) {
        model.addAttribute("page", service.findAllPage(pageNum));
        return "dept/findAll";
    }

    /**
     * 根据ID查询
     *
     * @param id 学院ID
     * @return 修改页面
     */
    @ApiOperation(value = "根据Id查询Department", notes = "根据ID查询Department方法详情", tags = {"DepartmentController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "", value = "学院Id", required = true, dataType = "Integer")
    })
    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("dept", service.findById(id));
        return "/dept/add";
    }

    /**
     * 修改学院信息
     *
     * @param department 学院
     * @return 显示全部页面
     */
    @ApiOperation(value = "Department修改", notes = "Department修改方法详情", tags = {"DepartmentController"})
    @PutMapping
    public String update(Department department) {
        // 修改
        service.update(department);
        return "redirect:/dept/page/1";
    }

    /**
     * 根据ID删除学院信息
     *
     * @param id 学院ID
     * @return 全部显示页面
     */
    @ApiOperation(value = "Department删除", notes = "Department删除方法详情", tags = {"DepartmentController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "", value = "i的学院ID", required = true, dataType = "Integer")
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/dept/page/1";
    }

    /**
     * 跳转到添加学院页面
     *
     * @return 添加
     */
    @ApiOperation(value = "Department添加页面", notes = "Department添加页面详情", tags = {"DepartmentController"})
    @GetMapping("/add")
    public String add() {
        // 添加学院页面
        return "/dept/add";
    }

    /**
     * 添加学院操作
     *
     * @param department 学员信息
     * @return 显示全部学院
     */
    @ApiOperation(value = "Department添加操作方法", notes = "Department添加方法详情", tags = {"DepartmentController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "department", value = "部门信息", required = true, dataType = "Department")
    })
    @PostMapping
    public String add(Department department) {
        service.add(department);
        return "redirect:/major/add";
    }
}
