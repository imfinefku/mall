package com.school.mall.sys.entity;

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

/**
 * 商品图片表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MallGoodsImg对象", description="商品图片表")
public class MallGoodsImg implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
  @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "图片类型,1主图 2附属图")
    private Integer imgType;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "图片描述")
    private String imgAddress;

    @ApiModelProperty(value = "图片")
    private String imgUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态 1启用,2禁用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}