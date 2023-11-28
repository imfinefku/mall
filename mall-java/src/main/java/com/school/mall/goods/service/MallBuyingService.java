package com.school.mall.goods.service;

import com.school.mall.goods.entity.MallBuying;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.mall.goods.entity.vo.MallBuyingVo;

/**
 * 求购信息表 服务类
 */
public interface MallBuyingService extends IService<MallBuying> {

    void saveMallBuyingVo(MallBuyingVo mallBuyingVo);

}
