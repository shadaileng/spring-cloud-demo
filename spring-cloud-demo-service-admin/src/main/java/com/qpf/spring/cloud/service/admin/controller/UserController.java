package com.qpf.spring.cloud.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.service.admin.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始下标"),
            @ApiImplicitParam(name = "length", value = "每页元素数量"),
            @ApiImplicitParam(name = "userJson", value = "条件")
    })
    @GetMapping("page")
    public BaseResult page(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "2") Integer length,
            @RequestParam(required = false) String userJson) {
        BaseResult result;
        User user = null;
        try {
            if (StringUtils.isNotBlank(userJson)) {
                user = JsonUtils.json2pojo(userJson, User.class);
            }
            PageInfo page = userService.page(start, length, user);

            BaseResult.Cursor cursor = new BaseResult.Cursor();
            cursor.setLimit(page.getPageSize());
            cursor.setOffset(page.getPageNum());
            cursor.setTotal(new Long(page.getTotal()).intValue());

            result = BaseResult.OK(page.getList(), cursor);
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }

        return result;
    }
    @ApiOperation("保存用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userJson", value = "用户Json字符串", dataTypeClass = String.class)
    })
    @PostMapping("save")
    private BaseResult save(@RequestParam String userJson) {
        BaseResult result;
        try {
            User user = JsonUtils.json2pojo(userJson, User.class);
            result = userService.save(user, "loginCode", "email", "phone");
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }

    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "用户Id数组")
    })
    @DeleteMapping("delete")
    private BaseResult delete(@RequestParam("ids") Integer[] ids) {
        BaseResult result;
        try {
            result = userService.delete(ids);
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }

    @ApiOperation("根据Id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", dataTypeClass = Integer.class)
    })
    @GetMapping("{id}")
    private BaseResult getUserById(@PathVariable(value = "id") Integer id) {
        BaseResult result;
        try {
            User user = new User();
            user.setId(id);
            result = userService.getById(user);
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }
    @ApiOperation("用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginCode", value = "用户账号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "登陆密码", dataTypeClass = String.class)
    })
    @GetMapping("login")
    public BaseResult login(String loginCode, String password) {
        BaseResult result;
        try {
            result = userService.login(loginCode, password);
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }

}
