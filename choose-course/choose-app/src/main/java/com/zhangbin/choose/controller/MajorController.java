package com.zhangbin.choose.controller;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Department;
import com.zhangbin.choose.pojo.Major;
import com.zhangbin.choose.pojo.MajorInfo;
import com.zhangbin.choose.service.DepartmentService;
import com.zhangbin.choose.service.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "MajorController")
@Controller
@RequestMapping("/major")
@CrossOrigin
public class MajorController {

    @Autowired
    private MajorService service;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 通过院系ID获取专业信息  以JSON格式返回
     *
     * @param id 院系ID
     * @return Major集合
     */

    @ApiOperation(value = "通过学院ID查询专业方法", notes = "通过学院ID查询专业方法详情", tags = {"MajorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "学院ID", required = true, dataType = "Integer")
    })
    @GetMapping("/dept/{id}")
    @ResponseBody
    public List<Major> findByDepartmentId(@PathVariable Integer id) {
        return service.findByDepartmentId(id);
    }

    /**
     * 查询专业的信息
     *
     * @param pageNum 页数
     * @return 查询全部专业页面
     */
    @ApiOperation(value = "分页查询专业", notes = "分页查询专业方法详情", tags = {"MajorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer")
    })
    @GetMapping("/page/{pageNum}")
    public String findInfoAll(@PathVariable int pageNum, Model model) {
        PageInfo<MajorInfo> page = service.findAllInfo(pageNum);
        model.addAttribute("page", page);
        return "major/findAll";
    }

    /**
     * 根据ID查找MajorInfo
     *
     * @param id Majorid
     * @return 修改页面
     */
    @ApiOperation(value = "跳转到修改页面", notes = "跳转到修改页面方法详情", tags = {"MajorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "专业Id", required = true, dataType = "Integer")
    })
    @GetMapping("/{id}")
    public String findInfoById(@PathVariable int id, Model model) {
        MajorInfo major = service.findInfoById(id);
        List<Department> depts = departmentService.findAll();
        model.addAttribute("major", major);
        model.addAttribute("depts", depts);
        return "major/add";
    }

    /**
     * 修改专业信息
     *
     * @param major 专业信息
     * @return 全部专业显示页面
     */
    @ApiOperation(value = "修改Major方法", notes = "修改Major方法详情", tags = {"MajorController"})
    @PutMapping
    public String update(Major major) {
        service.update(major);
        return "redirect:/major/page/1";
    }

    /**
     * 跳转到添加专业页面
     *
     * @return 添加专业页面
     */
    @ApiOperation(value = "跳转到添加页面", notes = "跳转到添加页面", tags = {"MajorController"})
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("depts", departmentService.findAll());
        return "/major/add";
    }

    /**
     * 添加专业
     *
     * @param major 要添加的专业信息
     * @return 全部专业显示
     */
    @ApiOperation(value = "添加专业方法", notes = "添加专业方法详情", tags = {"MajorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "major", value = "Major专业", required = true, dataType = "Major")
    })
    @PostMapping
    public String add(Major major) {
        service.add(major);
        return "redirect:/major/page/1";
    }

    /**
     * 删除专业
     *
     * @param id 删除的专业ID
     * @return 查询全部页面
     */
    @ApiOperation(value = "删除专业方法", notes = "删除专业方法详情", tags = {"MajorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "专业ID", required = true, dataType = "Integer")
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/major/page/1";
    }

}
