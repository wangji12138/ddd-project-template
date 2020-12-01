package cn.changemax.ddd.appliction.dto.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author WangJi
 * @Description 用户信息
 * @Date 2020/11/30 16:23
 */
@Accessors(chain = true)
@Data
@ApiModel
@NoArgsConstructor
public class UserOutputDTO {

    @ApiModelProperty(notes = "用户ID")
    private String id;

    @ApiModelProperty(notes = "用户名称")
    private String name;

}
