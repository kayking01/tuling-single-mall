package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    PmsProductAttributeCategoryService attributeCategoryService;


    /**
     * @Author wkx
     * @Description // 商品类型 查询
     * @Date 9:50 2024/11/14
     * @Param [pageNum, pageSize]
     * @return CommonResult
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        Page page = attributeCategoryService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * @Author wkx
     * @Description //商品类型的新增 form Data数据直接接收
     * @Date 10:37 2024/11/13
     * @Param [productAttributeCategory]
     * @return com.tulingxueyuan.mall.common.api.CommonResult
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(PmsProductAttributeCategory productAttributeCategory) {

        boolean result = attributeCategoryService.add(productAttributeCategory);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }


    /**
     * 商品类型修改
     * return request({
     * url:'/productAttribute/category/update/'+id,
     * method:'post',
     * data:data
     * })
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(PmsProductAttributeCategory productAttributeCategory) {

        boolean result = attributeCategoryService.updateById(productAttributeCategory);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }


    /**
     * 商品类型删除
     * url:'/productAttribute/category/delete/'+id,
     * method:'get'
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {

        boolean result = attributeCategoryService.removeById(id);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }

    }

    @RequestMapping(value="/list/withAttr",method = RequestMethod.GET)
    public CommonResult getListWithAttr(){
        List<ProductAttributeCateDTO> list= attributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }

}

