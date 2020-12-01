package cn.changemax.ddd.infrastructure.base;

import java.io.Serializable;

/**
 * Mapper公共基类，由MybatisGenerator自动生成请勿修改
 *
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK>    The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 * @author WANGJI
 */
public interface MyBatisBaseMapper<Model, PK extends Serializable> {
    /**
     * 根据主键删除对象
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 插入对象
     *
     * @param record
     * @return
     */
    int insert(Model record);

    /**
     * 选择插入对象
     *
     * @param record
     * @return
     */
    int insertSelective(Model record);

    /**
     * 根据主键查询对象
     *
     * @param id
     * @return
     */
    Model selectByPrimaryKey(PK id);

    /**
     * 选择更新对象
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Model record);

    /**
     * 更新对象
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Model record);
}