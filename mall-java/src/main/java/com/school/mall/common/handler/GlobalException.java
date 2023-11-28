package com.school.mall.common.handler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "全局异常")
public class GlobalException extends RuntimeException {

  @ApiModelProperty(value = "状态码")
  private Integer code;
  private String message;




}