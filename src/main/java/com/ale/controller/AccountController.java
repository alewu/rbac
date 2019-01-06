package com.ale.controller;

import com.ale.common.response.Response;
import com.ale.entity.User;
import com.ale.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  User 控制器
 * @author alewu
 * @date 2018-06-05 09:36:37
 */
@Api(value = "账号控制器", description = "")
@Validated
@RequestMapping("/accounts")
@RestController
public class AccountController {
    @Autowired
    private UserService userService;

    /**
     * @author alewu
     * @date 2018-06-05 09:36:37
     * @description 增加单个User
     */
    @ApiOperation(value = "增加单个User", notes = "")
    @PostMapping("/register")
    public Response saveUser(@Validated @RequestBody User user) {
        userService.insert(user);
        return Response.ok().put("userId", user.getUserId());
    }

//    /**
//     * @author alewu
//     * @date 2018-06-05 09:36:37
//     * @description 查询单个User
//     */
//    @ApiOperation(value = "查询单个User", notes = "")
//    @ApiImplicitParam(name = "userId", value = "", required = true, dataType = "Long")
//    @GetMapping(USERS + USER_ID)
//    public Response getUser(@PathVariable String userId) {
//        User user = userService.get(userId);
//        return Response.ok().put("user", user);
//    }
//
//    /**
//     * @author alewu
//     * @date 2018-06-05 09:36:37
//     * @description 更新单个User
//    */
//    @ApiOperation(value = "更新单个User", notes = "")
//    @PutMapping(USERS)
//    public Response updateUser(@Validated User user) {
//        userService.update(user);
//        return Response.ok();
//    }
//

}


