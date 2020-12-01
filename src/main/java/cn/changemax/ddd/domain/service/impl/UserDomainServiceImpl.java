package cn.changemax.ddd.domain.service.impl;

import cn.changemax.ddd.appliction.dto.input.UserAddInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserDeleteInputDTO;
import cn.changemax.ddd.appliction.dto.input.UserEditInputDTO;
import cn.changemax.ddd.domain.model.CmUser;
import cn.changemax.ddd.domain.service.UserDomainService;
import cn.changemax.ddd.infrastructure.constart.CmConst;
import cn.changemax.ddd.infrastructure.jpa.CmUserRepository;
import cn.changemax.ddd.interfaces.exception.CmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author WangJi
 * @Description 用户领域
 * @Date 2020/12/1 13:49
 */
@Slf4j
@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private CmUserRepository cmUserRepository;

    @Override
    public void save(UserAddInputDTO inputDTO) {
        CmUser user = new CmUser(inputDTO.getName());
        cmUserRepository.save(user);
    }

    @Override
    public void delete(UserDeleteInputDTO inputDTO) {
        Optional<CmUser> optional = cmUserRepository.findByIdAndStatus(inputDTO.getId(), CmConst.EntityStatus.NORMAL);
        if (!optional.isPresent()) {
            throw new CmException("用户未找到！");
        }
        cmUserRepository.save(optional.get().delete());
    }

    @Override
    public void update(UserEditInputDTO inputDTO) {
        Optional<CmUser> optional = cmUserRepository.findByIdAndStatus(inputDTO.getId(), CmConst.EntityStatus.NORMAL);
        if (!optional.isPresent()) {
            throw new CmException("用户未找到！");
        }
        cmUserRepository.save(optional.get().update(inputDTO.getName()));
    }

    @Override
    public CmUser getUser(String userId) {
        Optional<CmUser> optional = cmUserRepository.findByIdAndStatus(userId, CmConst.EntityStatus.NORMAL);
        if (!optional.isPresent()) {
            throw new CmException("用户未找到！");
        }
        return optional.get();
    }

    @Override
    public List<CmUser> getUser() {
        return cmUserRepository.findByStatus(CmConst.EntityStatus.NORMAL);
    }
}
