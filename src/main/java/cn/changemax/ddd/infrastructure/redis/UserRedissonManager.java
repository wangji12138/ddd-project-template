package cn.changemax.ddd.infrastructure.redis;

import cn.changemax.ddd.domain.model.CmUser;
import cn.changemax.ddd.infrastructure.constart.RedisKeys;
import cn.changemax.ddd.infrastructure.utils.CmUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJi
 * @Description 用户缓存管理类
 * @Date 2020/12/1 14:41
 */
@Service
public class UserRedissonManager {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取桶中对象
     * @param userId
     * @return
     */
    public CmUser getCmUser(String userId) {
        String redisKey = CmUtils.generateRedisKey(RedisKeys.User.USER_INFO, userId);
        RBucket<CmUser> bucket = redissonClient.getBucket(redisKey);
        return bucket.isExists() ? bucket.get() : null;
    }

    /**
     * 设置桶中对象
     * @param user
     */
    public void setCmUser(CmUser user) {
        String redisKey = CmUtils.generateRedisKey(RedisKeys.User.USER_INFO, user.getId());
        RBucket<CmUser> bucket = redissonClient.getBucket(redisKey);
        bucket.set(user, 3, TimeUnit.DAYS);
    }

    /**
     * 删除桶中对象
     * @param userId
     * @return
     */
    public boolean delCmUser(String userId) {
        String redisKey = CmUtils.generateRedisKey(RedisKeys.User.USER_INFO, userId);
        RBucket<CmUser> bucket = redissonClient.getBucket(redisKey);
        return bucket.delete();
    }
}
