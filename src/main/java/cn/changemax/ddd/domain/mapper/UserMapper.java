package cn.changemax.ddd.domain.mapper;

import cn.changemax.ddd.domain.model.CmUser;
import cn.changemax.ddd.infrastructure.base.MyBatisBaseMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangJi
 * @Description 用户持久层接口
 * @Date 2020/12/1 15:18
 */
@Repository
public interface UserMapper extends MyBatisBaseMapper<CmUser, String> {

    /**
     * 模糊查询用户
     * @param name
     * @return
     */
    List<CmUser> listUser(@Param(value = "name") String name);

}
