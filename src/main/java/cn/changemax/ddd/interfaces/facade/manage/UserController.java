package cn.changemax.ddd.interfaces.facade.manage;

import cn.changemax.ddd.appliction.dto.input.UserAddInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserDeleteInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserEditInputDTO;
import cn.changemax.ddd.appliction.dto.output.UserOutputDTO;
import cn.changemax.ddd.appliction.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author WangJi
 * @Description 用户controller
 * @Date 2020/11/30 15:42
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/v1/user")
    @ApiOperation(value = "增加用户", tags = {"资料中心-用户管理"})
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数错误"),
            @ApiResponse(code = 404, message = "接口路径不正确")
    })
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody @Valid UserAddInputDTO inputDTO) {
        userService.addUser(inputDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/v1/user")
    @ApiOperation(value = "删除用户", tags = {"资料中心-用户管理"})
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数错误"),
            @ApiResponse(code = 404, message = "接口路径不正确")
    })
    @ResponseBody
    public ResponseEntity<?> deleteUser(@RequestBody @Valid UserDeleteInputDTO inputDTO) {
        userService.deleteUser(inputDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/v1/user")
    @ApiOperation(value = "更新用户", tags = {"资料中心-用户管理"})
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数错误"),
            @ApiResponse(code = 404, message = "接口路径不正确")
    })
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserEditInputDTO inputDTO) {
        userService.updateUser(inputDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/v1/user/{userId}")
    @ApiOperation(value = "获取用户详情", tags = {"资料中心-用户管理"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataType = "String", required = true, value = "用户ID"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数错误"),
            @ApiResponse(code = 404, message = "接口路径不正确")
    })
    @ResponseBody
    public ResponseEntity<UserOutputDTO> getUserInfo(@PathVariable("userId") String userId) {
        UserOutputDTO outputDTO = userService.getUserInfo(userId);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping(value = "/v1/user/info")
    @ApiOperation(value = "获取用户列表", tags = {"资料中心-用户管理"})
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数错误"),
            @ApiResponse(code = 404, message = "接口路径不正确")
    })
    @ResponseBody
    public ResponseEntity<List<UserOutputDTO>> listUserInfo() {
        List<UserOutputDTO> outputDTOS = userService.listUserInfo();
        return ResponseEntity.ok(outputDTOS);
    }

}
