package cn.changemax.ddd.infrastructure.utils;

import cn.changemax.ddd.infrastructure.constart.RedisKeys;

/**
 * @author WangJi
 * @Description cm工具类
 * @Date 2020/12/1 14:44
 */
public final class CmUtils {

    /**
     * 生成Redis的主键
     *
     * @param params 参数
     */
    public static String generateRedisKey(String... params) {
        StringBuilder sb = new StringBuilder(RedisKeys.REDIS_PREFIX);
        if (params != null && params.length > 0) {
            for (String key : params) {
                sb.append(RedisKeys.REDIS_SPLIT).append(key);
            }
        }
        return sb.toString();
    }


}
