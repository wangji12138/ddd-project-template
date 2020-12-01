package cn.changemax.ddd.domain.service;

import cn.changemax.ddd.appliction.dto.input.UserAddInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserDeleteInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserEditInputDTO;
import cn.changemax.ddd.domain.model.CmUser;

import java.util.List;

/**
 * @author WangJi
 * @Description 用户领域
 * @Date 2020/12/1 13:49
 */
public interface UserDomainService {
    /**
     * 保存用户
     * @param inputDTO
     */
    void save(UserAddInputDTO inputDTO);

    /**
     * 删除用户
     * @param inputDTO
     */
    void delete(UserDeleteInputDTO inputDTO);

    /**
     * 更新用户
     * @param inputDTO
     */
    void update(UserEditInputDTO inputDTO);

    /**
     * 获取用户
     * @param userId
     * @return
     */
    CmUser getUser(String userId);

    /**
     * 获取用户
     * @return
     */
    List<CmUser> getUser();
}
