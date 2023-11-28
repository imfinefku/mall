package com.school.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*评分*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommentGoods对象", description="评分")
public class CommentGoods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "组件")
  @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long mallGoodsId;

    @ApiModelProperty(value = "用户id")
    private Long goodsUserId;

    @ApiModelProperty(value = "评论")
    private String news;

    @ApiModelProperty(value = "评分1-5")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;


}