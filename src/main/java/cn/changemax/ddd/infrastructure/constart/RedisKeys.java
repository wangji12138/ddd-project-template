package cn.changemax.ddd.infrastructure.constart;

/**
 * @author WangJi
 * @Description redis key
 * @Date 2020/12/1 14:47
 */
public final class RedisKeys {

    /**
     * ChangeMax redis key前缀，所有存进Redis中的key都会带此前缀
     */
    public final static String REDIS_PREFIX = "ChangeMax";
    /**
     * redis key分隔符
     */
    public final static String REDIS_SPLIT = ":";

    /**
     * 用户相关key
     */
    public interface User {
        /**
         * 用户基本信息
         */
        String USER_INFO = "user_info";
    }
}
