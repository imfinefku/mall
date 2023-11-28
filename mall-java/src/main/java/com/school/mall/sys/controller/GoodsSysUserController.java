package com.school.mall.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.mall.common.result.R;
import com.school.mall.common.utils.FieldAssert;
import com.school.mall.goods.entity.User;
import com.school.mall.goods.entity.queryVo.UserQueryVo;
import com.school.mall.goods.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商城用户表 前端控制器
 */
@RestController
@RequestMapping("/sys/goodsUser")
@Api(tags = "商城用户表")
public class GoodsSysUserController {

    @Autowired
    UserService iGoodsUserService;

    /**
     * 查询商城用户表列表
     */
    @PostMapping("queryGoodsUserList")
    @ApiOperation("商城用户表列表")
    public R queryGoodsUserList(@RequestBody UserQueryVo queryVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = iGoodsUserService.page(queryVo.getPage(), queryWrapper);
        return R.ok().data("dataList", page);
    }

    /**
     * 保存或者更新商城用户表信息
     */
    @PostMapping("saveOrUpdateGoodsUser")
    @ApiOperation("保存或者更新商城用户表信息")
    public R saveOrUpdateGoodsUser(@RequestBody User entity) {
        boolean b = true;
        if (StringUtils.isEmpty(entity.getId())) {
            b = iGoodsUserService.save(entity);
        } else {
            b = iGoodsUserService.updateById(entity);
        }
        return R.th(b);
    }


    /**
     * 根据id查询商城用户表信息
     */
    @GetMapping("getByIdGoodsUser/{id}")
    @ApiOperation("根据id查询商城用户表信息")
    public R getByIdGoodsUser(@PathVariable("id") String id) {
        User dataGoodsUser = iGoodsUserService.getById(id);
        return R.ok().data("entity", dataGoodsUser);

    }

    /**
     * 根据id删除商城用户表信息
     */
    @DeleteMapping("removeByIdGoodsUser/{id}")
    @ApiOperation("根据id删除商城用户表信息")
    public R removeByIdGoodsUser(@PathVariable("id") String id) {
        boolean effect = iGoodsUserService.removeById(id);
        return R.th(effect);

    }


    /**
     * 查询携带余额的用户信息
     */
    @GetMapping("getByCarryMoneyDetail/{id}")
    public R getByCarryMoneyDetail(@PathVariable("id") Long id) {
        return R.ok().data("dataInfo",iGoodsUserService.getByCarryMoneyDetail(id));
    }


    @PostMapping("uptUserInfo")
    public R uptUserInfo(@RequestBody User user) {
        if (!StringUtils.isEmpty(user.getUsername())){
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("username",user.getUsername());
             iGoodsUserService.list(queryWrapper).forEach(res->{
                 FieldAssert.isTrue(res.getId().equals(user.getId()),"用户已存在");
            });
        }
        iGoodsUserService.updateById(user);
        return R.ok();
    }

}

