package cn.changemax.ddd.appliction.assembler;

import cn.changemax.ddd.appliction.dto.output.UserOutputDTO;
import cn.changemax.ddd.domain.model.CmUser;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangJi
 * @Description 用户转换类
 * @Date 2020/12/1 13:51
 */
public final class UserAssembler {

    /**
     * cmUser to userOutputDTO
     * @param user
     * @return
     */
    public static UserOutputDTO toUserOutputDTO(CmUser user) {
        UserOutputDTO outputDTO = new UserOutputDTO();
        BeanUtils.copyProperties(outputDTO, user);
        return outputDTO;
    }

    /**
     * cmUser to userOutputDTO
     * @param users
     * @return
     */
    public static List<UserOutputDTO> toUserOutputDTO(List<CmUser> users) {
        return users.stream().map(UserAssembler::toUserOutputDTO).collect(Collectors.toList());
    }
}
