package cn.changemax.ddd.appliction.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author WangJi
 * @Description 用户新增
 * @Date 2020/12/1 13:36
 */
@Accessors(chain = true)
@Data
@ApiModel
@NoArgsConstructor
public class UserAddInputDTO {

    @ApiModelProperty(notes = "用户名称")
    private String name;
}
