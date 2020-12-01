package cn.changemax.ddd.interfaces.facade;

import cn.changemax.ddd.appliction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangJi
 * @Description 登录接口
 * @Date 2020/11/30 15:44
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;


}
