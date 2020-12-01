package cn.changemax.ddd.appliction.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author WangJi
 * @Description 删除用户
 * @Date 2020/12/1 13:42
 */
@Accessors(chain = true)
@Data
@ApiModel
@NoArgsConstructor
public class UserDeleteInputDTO {

    @ApiModelProperty(notes = "用户ID")
    private String id;
}
