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
@RequestMapping("v1/api")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始下标", required = false),
            @ApiImplicitParam(name = "length", value = "每页元素数量", required = false),
            @ApiImplicitParam(name = "userJson", value = "条件", required = false)
    })
    @GetMapping("page")
    public BaseResult page(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "2") Integer length,
            @RequestParam(required = false) String userJson) {
        BaseResult result = null;
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
    private BaseResult save(@RequestParam(required = true) String userJson) {
        BaseResult result = null;
        try {
            User user = JsonUtils.json2pojo(userJson, User.class);
            result = userService.save(user);
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }

    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", dataTypeClass = Integer.class)
    })
    @DeleteMapping("delete")
    private BaseResult delete(Integer id) {
        BaseResult result = null;
        try {
            User user = new User();
            user.setId(id);
            result = userService.delete(user);
        } catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }
}
