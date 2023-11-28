package com.school.mall.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.mall.common.result.R;
import com.school.mall.common.utils.UserInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.school.mall.goods.entity.Address;
import com.school.mall.goods.service.AddressService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/goods/address")
@Api(tags = "用户地址表")
public class AddressController {

    @Autowired
    AddressService iAddressService;

    /*查询用户地址表列表*/
    @PostMapping("queryAddressList")
    @ApiOperation("用户地址表列表")
    public R queryAddressList() {

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_user_id", UserInfoUtil.getUserId()).orderByDesc("gmt_create");

        Page<Address> page = new Page<>();
        page.setSize(2);
        page.setCurrent(1);

        Page<Address> list = iAddressService.page(page, queryWrapper);
        return R.ok().data("dataList", list.getRecords());
    }

    /*保存或者更新用户地址表信息*/
    @PostMapping("saveOrUpdateAddress")
    @ApiOperation("保存或者更新用户地址表信息")
    public R saveOrUpdateAddress(@RequestBody Address entity) {
        boolean b = true;
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setGmtCreate(new Date());
            entity.setGoodsUserId(Long.valueOf(UserInfoUtil.getUserId()));
            b = iAddressService.save(entity);
        } else {
            b = iAddressService.updateById(entity);
        }
        return R.th(b);
    }


    /*根据id查询用户地址表信息*/
    @GetMapping("getByIdAddress/{id}")
    @ApiOperation("根据id查询用户地址表信息")
    public R getByIdAddress(@PathVariable("id") String id) {
        Address dataAddress = iAddressService.getById(id);
        return R.ok().data("entity", dataAddress);

    }

    /*根据id删除用户地址表信*/
    @DeleteMapping("removeByIdAddress/{id}")
    @ApiOperation("根据id删除用户地址表信息")
    public R removeByIdAddress(@PathVariable("id") String id) {
        boolean effect = iAddressService.removeById(id);
        return R.th(effect);

    }

}

