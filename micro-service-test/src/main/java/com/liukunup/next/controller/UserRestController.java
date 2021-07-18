package com.liukunup.next.controller;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.UserDO;
import com.liukunup.next.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户
 * 管理服务控制器
 *
 * @author Liu Kun
 * @since 2021-07-18 22:56:56
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    IUserService userServiceImpl;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> addUser(@RequestBody UserDO user) {
        return ApiResult.check(userServiceImpl.insertUser(user), user);
    }

    @RequestMapping(value="/del", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> delUser(@RequestBody UserDO user) {
        return ApiResult.check(userServiceImpl.deleteUser(user), user);
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> updateUser(@RequestBody UserDO user) {
        return ApiResult.check(userServiceImpl.updateUser(user), user);
    }

    @RequestMapping(value="/find", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> selectUser(@RequestBody UserDO user) {
        UserDO tmpUser = userServiceImpl.selectUser(user);
        return ApiResult.check((null != tmpUser), tmpUser);
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody
    ApiResult<Object> listUser(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        List<UserDO> tmpUserList = userServiceImpl.listUser(pageNo, pageSize);
        return ApiResult.check((null != tmpUserList), tmpUserList);
    }
}
