package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.dto.ProductCateChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * @Author wkx
     * @Description // 获取商品分类列表
     * @Date 11:38 2024/11/12
     * @Param [parentId, pageNum, pageSize]
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page
     **/
    Page list(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * @Author wkx
     * @Description // 修改导航栏显示状态
     * @Date 16:06 2024/11/12
     * @Param [ids, navStatus]
     * @return boolean
     **/
    boolean updateNavStatus(List<Long> ids,Integer navStatus);
    /**
     * @Author wkx
     * @Description // 修改导航栏显示状态
     * @Date 16:06 2024/11/12
     * @Param [ids, navStatus]
     * @return boolean
     **/
    boolean updateShowStatus(List<Long> ids,Integer showStatus);

    boolean customSave(PmsProductCategoryDTO productCategoryDTO);

    boolean update(PmsProductCategoryDTO productCategoryDTO);

    /**
     * 获取商品一级分类和二级分类的下拉级联数据
     * @return
     */
    List<ProductCateChildrenDTO> getWithChildren();
}
