package com.school.mall.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.mall.sys.entity.MallGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.mall.sys.entity.MallGoodsOrder;
import com.school.mall.sys.entity.queryVo.MallIdleGoodesSaveVo;
import com.school.mall.sys.entity.vo.MallGoodsVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 商品表 服务类
 */
public interface MallGoodsService extends IService<MallGoods> {

    IPage<MallGoodsVo> queryPage(Page page, MallGoods mallGoods);

    Boolean lessStock(List<MallGoodsOrder> list);


    void  saveIdleGoods(MallIdleGoodesSaveVo mallIdleGoodesSaveVo);


}
