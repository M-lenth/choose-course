package com.zhangbin.choose.controller;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.User;
import com.zhangbin.choose.pojo.UserInfo;
import com.zhangbin.choose.service.UserService;
import com.zhangbin.choose.util.StringUtil;
import com.zhangbin.choose.pojo.Department;
import com.zhangbin.choose.service.DepartmentService;
import com.zhangbin.choose.service.MajorService;
import com.zhangbin.choose.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Api(value = "UserController")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private MajorService majorService;

    @Autowired
    private DepartmentService departmentService;

    /*-----------------------------------用户公共操作-----------------------------------------------------------*/

    /**
     * 登录操作
     *
     * @return 返回到首页或者是登陆页面
     */
    @ApiOperation(value = "登录方法", notes = "登录方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "param", name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "param", name = "password", value = "用户密码", required = true, dataType = "String")
    })
    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("password") String password,
                        HttpSession session, Model model) {
        // sha256加密
        String encode = StringUtil.sha256Encode(password);
        // 查询用户信息
        UserInfo info = service.findUserInfoById(id);
        User user = info.getUser();
        if (user != null) {
            // user不为空的情况
            if (user.getPassword().equals(encode)) {
                // 密码相同 登陆成功
                session.setAttribute("user", info);
                return "redirect:/index";      //跳转到 index.html
            }
            model.addAttribute("msg", "密码不正确，请确认密码");
        } else {
            model.addAttribute("msg", "未找到该账号，请确认账号是否正确");
        }
        return "/user/login";      //返回到 login.html
    }

    /**
     * 跳转到用户信息页面
     *
     * @return 用户信息页面
     */
    @ApiOperation(value = "查看用户详细信息页", notes = "查看用户详细信息页方法详情", tags = {"UserController"})
    @GetMapping("/info")
    public String userInfo() {
        return "user/userInfo";
    }

    /**
     * 跳转到修改密码界面
     *
     * @return 修改密码界面
     */
    @ApiOperation(value = "跳转到修改密码界面", notes = "跳转到修改密码界面方法详情", tags = {"UserController"})
    @GetMapping("/update-pwd")
    public String updatePwd() {
        return "/user/update-pwd";
    }

    /**
     * 修改密码操作
     *
     * @param newPassword 新密码
     * @return 登陆界面login.html
     */
    @ApiOperation(value = "修改密码操作", notes = "修改密码操作方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "param", name = "newPassword", value = "新密码", required = true, dataType = "String"),
    })
    @PostMapping("/update-pwd")
    public String updatePwd(@RequestParam String newPassword, HttpSession session) {
        UserInfo info = (UserInfo) session.getAttribute("user");
        // 密码加密
        User user = info.getUser();
        user.setPassword(StringUtil.sha256Encode(newPassword));
        service.update(user);
        session.removeAttribute("user");
        return "redirect:/login";
    }

    /**
     * 检查密码
     *
     * @param oldPassword 旧密码
     * @return 密码对错
     */
    @ApiOperation(value = "检查密码", notes = "检查密码方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "param", name = "oldPassword", value = "旧密码", required = true, dataType = "String")
    })
    @PostMapping("/checkPwd")
    @ResponseBody
    public Boolean checkPass(HttpSession session, String oldPassword) {
        // 旧密码
        String password = ((UserInfo) session.getAttribute("user")).getUser().getPassword();
        // 输入的密码加密后检查
        oldPassword = StringUtil.sha256Encode(oldPassword);
        //判断密码是否相同 返回到页面
        if (password.equals(oldPassword)) {
            System.out.println("true  old:" + oldPassword);
            return true;
        } else {
            // 否则返回 false，表示错误，则不能修改密码
            System.out.println("false  old:" + oldPassword);
            return false;
        }
    }


    /**
     * 登出账号操作
     *
     * @return 登陆界面 login.html
     */
    @ApiOperation(value = "登出账号操作", notes = "登出账号操作方法详情", tags = {"UserController"})
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }

    /*-----------------------------------管理员操作-----------------------------------------------------------*/

    /**
     * 分页查询所有用户
     *
     * @param pageNum 页数
     * @param type    用户类型
     */
    @ApiOperation(value = "分页查询所有用户", notes = "分页查询所有用户方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "type", value = "用户类型", required = true, dataType = "Integer")
    })
    @GetMapping("/{pageNum}/{type}")
    public String findPageByType(@PathVariable int pageNum, @PathVariable Integer type, Model model) {
        // 固定每页十条数据
        PageInfo<UserInfo> page1 = service.findByType(pageNum, PageUtil.SIZE, type);
        model.addAttribute("page", page1);
        model.addAttribute("type", type);
        return "/user/findAll";
    }

    /**
     * 通过ID查询用户
     *
     * @param id 用户ID
     * @return User
     */
    @ApiOperation(value = "通过ID查询用户", notes = "通过ID查询用户方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "", value = "用户ID", required = true, dataType = "String")
    })
    @GetMapping("/{id}")
    public String findById(@PathVariable String id, Model model) {
        // 查询UserInfo
        model.addAttribute("user", service.findUserInfoById(id));
        // 查询院系
        List<Department> departments = departmentService.findAll();
        model.addAttribute("depts", departments);
        // 查询第一个院系的专业
        model.addAttribute("majors", majorService.findByDepartmentId(departments.get(0).getId()));
        return "/user/add";
    }

    /**
     * 修改用户
     *
     * @return 查询全部页面
     */
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "parameter", name = "user", value = "修改的信息", required = true, dataType = "User")
    })
    @PutMapping
    public String update(User user) {
        // System.out.println(user);
        service.update(user);
        return "redirect:/user/1/-1";
    }

    /**
     * 添加用户
     *
     * @param user 需要添加的信息
     * @return 查询全部页面
     */
    @ApiOperation(value = "添加用户", notes = "添加用户方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "parameter", name = "user", value = "添加的信息", required = true, dataType = "User")
    })
    @PostMapping
    public String add(User user) {
        service.add(user);
        return "redirect:/user/1/-1";
    }

    /**
     * 跳转到添加页面
     *
     * @return 添加页面
     */
    @ApiOperation(value = "跳转到添加页面", notes = "跳转到添加页面方法详情", tags = {"UserController"})
    @GetMapping("/add")
    public String add(Model model) {
        // 查询院系
        List<Department> departments = departmentService.findAll();
        model.addAttribute("depts", departments);
        // 查询第一个院系的专业
        model.addAttribute("majors", majorService.findByDepartmentId(departments.get(0).getId()));
        return "/user/add";
    }

    /**
     * 查询所有用户
     *
     * @return User集合
     */
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户方法详情", tags = {"UserController"})
    @GetMapping("/all")
    @ResponseBody
    public List<User> findAll() {
        return service.findAll();
    }

    /**
     * 根据ID删除用户信息
     *
     * @param id 用户ID
     */
    @ApiOperation(value = "根据ID删除用户信息", notes = "根据ID删除用户信息方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/user/1/-1";
    }

    /*-----------------------------------教师操作-----------------------------------------------------------*/

    /**
     * 通过教师Id查询该教师所任课程的学生
     *
     * @param id 教师ID
     * @return 学生
     */
    @ApiOperation(value = "通过教师Id查询该教师所任课程的学生", notes = "通过教师Id查询该教师所任课程的学生方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "教师ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "当前页", required = true, dataType = "Integer")
    })
    @GetMapping("/teacher/{id}/{pageNum}")
    public String findByTeacherId(@PathVariable String id, @PathVariable int pageNum, Model model) {
        model.addAttribute("page", service.findByTeacher(pageNum, id));
        return "/user/findAll";
    }


}
