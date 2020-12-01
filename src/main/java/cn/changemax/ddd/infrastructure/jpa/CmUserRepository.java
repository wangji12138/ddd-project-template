package cn.changemax.ddd.infrastructure.jpa;

import cn.changemax.ddd.domain.model.CmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author WangJi
 * @Description cm用户接口
 * @Date 2020/12/1 15:46
 */
@Repository
public interface CmUserRepository extends JpaRepository<CmUser, String> {

    /**
     * 根据id找到状态
     * @param id
     * @param status
     * @return
     */
    Optional<CmUser> findByIdAndStatus(String id, int status);

    /**
     * 获取所有用户
     * @param status
     * @return
     */
    List<CmUser> findByStatus(int status);
}
