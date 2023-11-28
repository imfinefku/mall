package com.school.mall.sys.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 商品表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MallGoods对象", description = "商品表")
public class MallGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "支付方式 1 在线,2货到付款+在线")
    private Long goodsPayType;

    @ApiModelProperty(value = "数量")
    private Long goodsAmount;

    @ApiModelProperty(value = "价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "优惠价格")
    private BigDecimal goodsDiscount;

    @ApiModelProperty(value = "商品类型关联mall_goods_type表")
    private String goodsTypeCode;

    @ApiModelProperty(value = "商品状态 1启用,2禁用")
    private Integer status;

    @ApiModelProperty(value = "产地")
    private String goodsOrigin;

    @ApiModelProperty(value = "描述")
    private String goodsAddress;

    @ApiModelProperty(value = "标签 关联参数表 GOODS_BQ")
    private String goodsMarkCode;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "排序")
    private Integer goodeUserType;

    @TableField(exist = false)
    private String imgUrl;


}