package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.dto.ProductUpdateInitDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    ProductUpdateInitDTO getUpdateInfo(Long id);
}
