package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {
    Page list(Integer pageNum, Integer pageSize);

    boolean add(PmsProductAttributeCategory productAttributeCategory);

    List<ProductAttributeCateDTO> getListWithAttr();
}
