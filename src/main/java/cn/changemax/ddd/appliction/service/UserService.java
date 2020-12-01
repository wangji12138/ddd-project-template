package cn.changemax.ddd.appliction.service;

import cn.changemax.ddd.appliction.assembler.UserAssembler;
import cn.changemax.ddd.appliction.dto.input.UserAddInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserDeleteInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserEditInputDTO;
import cn.changemax.ddd.appliction.dto.output.UserOutputDTO;
import cn.changemax.ddd.domain.model.CmUser;
import cn.changemax.ddd.domain.service.UserDomainService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author WangJi
 * @Description 用户业务
 * @Date 2020/11/30 15:45
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDomainService userDomainService;

    /**
     * 添加用户
     * @param inputDTO
     */
    public void addUser(UserAddInputDTO inputDTO) {
        userDomainService.save(inputDTO);
    }

    /**
     * 删除用户
     * @param inputDTO
     */
    public void deleteUser(UserDeleteInputDTO inputDTO) {
        userDomainService.delete(inputDTO);
    }

    /**
     * 更新用户
     * @param inputDTO
     */
    public void updateUser(UserEditInputDTO inputDTO) {
        userDomainService.update(inputDTO);
    }

    /**
     * 获取用户
     * @param userId
     * @return
     */
    public UserOutputDTO getUserInfo(String userId) {
        CmUser user = userDomainService.getUser(userId);
        if (null == user) {
            return new UserOutputDTO();
        }
        return UserAssembler.toUserOutputDTO(user);
    }

    /**
     * 获取所有用户
     * @return
     */
    public List<UserOutputDTO> listUserInfo() {
        List<CmUser> users = userDomainService.getUser();
        if (CollectionUtils.isEmpty(users)) {
            return Lists.newArrayList();
        }
        return UserAssembler.toUserOutputDTO(users);
    }
}
