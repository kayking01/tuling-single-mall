package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.dto.RelationAttrInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {


    List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cId);
}
