package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.dto.ProductCateChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    PmsProductCategoryAttributeRelationService relationService;

    @Autowired
    PmsProductCategoryMapper productCategoryMapper;
    @Override
    public Page list(Long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id",parentId); // 需要查看数据库对应列名，parent_id  推荐使用lambda表达式的方式。
        queryWrapper.lambda().eq(PmsProductCategory::getParentId,parentId);

        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNavStatus(List<Long> ids, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                // 需要更新的列
                .set(PmsProductCategory::getNavStatus,navStatus)
                // 条件
                .in(PmsProductCategory::getId,ids);

        return this.update(wrapper);
    }
    @Override
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        UpdateWrapper<PmsProductCategory> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                // 需要更新的列
                .set(PmsProductCategory::getShowStatus,showStatus)
                // 条件
                .in(PmsProductCategory::getId,ids);

        return this.update(wrapper);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean customSave(PmsProductCategoryDTO productCategoryDTO) {
        // 保存商品分类
        PmsProductCategory productCategory=new PmsProductCategory();
        // 通过BeanUtils 将productCategoryDTO的数据拷贝到productCategory
        // 为什么要拷贝：因为一定要通过this.save 去保存PmsProductCategory，因为只有它才映射了@TableName
        BeanUtils.copyProperties(productCategoryDTO,productCategory);
        // 由于商品数量 和级别 在表单中没有维护， 需要设置默认值
        productCategory.setProductCount(0);
        if(productCategory.getParentId()==0){
            productCategory.setLevel(0);
        }
        else {
            // 如果有多级分类，根据parentId查出商品分类获取level+1
            // 由于只有2级分类，直接设置为1
            productCategory.setLevel(1);
        }
        this.save(productCategory);
        saveAttrRelation(productCategoryDTO, productCategory);

        return true;
    }

    /**
     * 添加关联属性
     * @param productCategoryDTO
     * @param productCategory
     * @return
     */
    private boolean saveAttrRelation(PmsProductCategoryDTO productCategoryDTO, PmsProductCategory productCategory) {

        List<Long> productAttributeIdList = productCategoryDTO.getProductAttributeIdList();
        List<PmsProductCategoryAttributeRelation> list=new ArrayList<>();
        for (Long attrId : productAttributeIdList) {
            // 得到分类保存后的主键id,   保存商品分类筛选属性关系
            PmsProductCategoryAttributeRelation productCategoryAttributeRelation=new PmsProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            list.add(productCategoryAttributeRelation);

        }
        return relationService.saveBatch(list);
    }

    @Override
    public boolean update(PmsProductCategoryDTO productCategoryDTO) {
        // 保存商品分类
        PmsProductCategory productCategory = new PmsProductCategory();
        // 通过BeanUtils 将productCategoryDTO的数据拷贝到productCategory
        // 为什么要拷贝：因为一定要通过this.save 去保存PmsProductCategory，因为只有它才映射了@TableName
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            // 如果有多级分类，根据parentId查出商品分类获取level+1
            // 由于只有2级分类，直接设置为1
            productCategory.setLevel(1);
        }
        this.updateById(productCategory);


        // 删除已保存的关联属性—根据商品分类id删除
        QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId, productCategory.getId());
        relationService.remove(queryWrapper);

        saveAttrRelation(productCategoryDTO, productCategory);
        return true;
    }

    /**
     * 获取商品一级分类和二级分类的下拉级联数据
     * @return
     */
    @Override
    public List<ProductCateChildrenDTO> getWithChildren() {
        return productCategoryMapper.getWithChildren();
    }
}
