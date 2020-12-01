package cn.changemax.ddd.infrastructure.redis;

import cn.changemax.ddd.domain.model.CmUser;
import cn.changemax.ddd.infrastructure.constart.RedisKeys;
import cn.changemax.ddd.infrastructure.utils.CmUtils;
import cn.changemax.ddd.infrastructure.utils.GsonUtils;
import cn.changemax.ddd.infrastructure.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJi
 * @Description 用户缓存管理类
 * @Date 2020/12/1 14:41
 */
@Service
public class UserRedisManager {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取桶中对象
     * @param userId
     * @return
     */
    public CmUser getCmUser(String userId) {
        String redisKey = CmUtils.generateRedisKey(RedisKeys.User.USER_INFO, userId);
        String value = redisTemplate.opsForValue().get(redisKey);
        return StringUtils.isNotBlank(value) ? GsonUtils.stringToBean(value, CmUser.class) : null;
    }

    /**
     * 设置桶中对象
     * @param user
     */
    public void setCmUser(CmUser user) {
        String redisKey = CmUtils.generateRedisKey(RedisKeys.User.USER_INFO, user.getId());
        redisTemplate.opsForValue().set(redisKey, GsonUtils.beanToString(user), 3, TimeUnit.DAYS);
    }

    /**
     * 删除桶中对象
     * @param userId
     * @return
     */
    public boolean delCmUser(String userId) {
        String redisKey = CmUtils.generateRedisKey(RedisKeys.User.USER_INFO, userId);
        return redisTemplate.delete(redisKey);
    }


}
