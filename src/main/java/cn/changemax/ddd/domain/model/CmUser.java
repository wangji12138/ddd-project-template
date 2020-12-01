package cn.changemax.ddd.domain.model;

import cn.changemax.ddd.infrastructure.constart.CmConst;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author WangJi
 * @Description cm用户
 * @Date 2020/12/1 13:47
 */
@Entity
@Data
@Table(name = "cm_user")
@Accessors(chain = true)
@NoArgsConstructor
public class CmUser implements Serializable, Cloneable {

    private static final long serialVersionUID = -4049311989512736947L;

    /**
     * id
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户状态（1正常 2删除）
     */
    private int status;

    /**
     * 生成实体构造
     * @param name
     */
    public CmUser(String name) {
        this.name = name;
        this.status = CmConst.EntityStatus.NORMAL;
    }

    /**
     * 更新用户
     * @param name
     */
    public CmUser update(String name) {
        this.name = name;
        return this;
    }

    /**
     * 更新用户
     */
    public CmUser delete() {
        this.status = CmConst.EntityStatus.DELETE;
        return this;
    }
}
