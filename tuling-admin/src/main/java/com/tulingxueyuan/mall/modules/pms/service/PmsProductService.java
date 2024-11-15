package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ProductConditionDTO;
import com.tulingxueyuan.mall.dto.ProductSaveParamsDTO;
import com.tulingxueyuan.mall.dto.ProductUpdateInitDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page list(ProductConditionDTO condition);

    boolean updateStatus(Integer publishStatus, List<Long> ids, SFunction<PmsProduct, ?> getPublishStatus);


    boolean create(ProductSaveParamsDTO productSaveParamsDTO);

    ProductUpdateInitDTO getUpdateInfo(Long id);

    boolean update(ProductSaveParamsDTO productSaveParamsDTO);
}
