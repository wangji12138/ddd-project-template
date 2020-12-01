package cn.changemax.ddd.infrastructure.constart;

/**
 * @author WangJi
 * @Description 常量类
 * @Date 2020/12/1 13:59
 */
public final class CmConst {
    /**
     * 实体状态
     */
    public interface EntityStatus {
        /**
         * 正常
         */
        int NORMAL = 1;

        /**
         * 删除
         */
        int DELETE = 2;

        /**
         * 屏蔽
         */
        int SHIELD = 3;

    }
}
