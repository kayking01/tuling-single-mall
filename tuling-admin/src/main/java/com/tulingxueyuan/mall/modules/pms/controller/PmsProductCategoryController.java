package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.dto.ProductCateChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author wkx
 * @since 2024-11-12
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    PmsProductCategoryService pmsProductCategoryService;

    @RequestMapping(value = "/list/{parentId}",method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        Page page = pmsProductCategoryService.list(parentId,pageNum,pageSize);
        CommonPage commonPage = CommonPage.restPage(page);
        return  CommonResult.success(commonPage);
    }

    @RequestMapping(value = "/update/navStatus",method = RequestMethod.POST)
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("navStatus") Integer navStatus){
        boolean result = pmsProductCategoryService.updateNavStatus(ids, navStatus);
        if (result){
            return CommonResult.success("修改成功");
        }else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/showStatus",method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("showStatus") Integer showStatus){
        boolean result = pmsProductCategoryService.updateShowStatus(ids, showStatus);
        if (result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult deleteProductCategory(@PathVariable("id") Long id){
        boolean result = pmsProductCategoryService.removeById(id);
        if (result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }
    }

    /**
     * @Author wkx
     * @Description // 商品分类新增  @RequestBody 用于 JSON数据 的接收
     * @Date 10:35 2024/11/13
     * @Param [productCategoryDTO]
     * @return com.tulingxueyuan.mall.common.api.CommonResult
     **/
    @RequestMapping(value="/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsProductCategoryDTO productCategoryDTO){

        boolean result = pmsProductCategoryService.customSave(productCategoryDTO);
        if(result){
            return CommonResult.success(result);
        }
        else {
            return CommonResult.failed();
        }

    }

    /**
     * 修改
     *   url:'/productCategory/update/'+id,
     *     method:'post',
     *     data:data
     *
     */
    @RequestMapping(value="/update/{id}",method = RequestMethod.POST)
    public CommonResult update(
            @RequestBody PmsProductCategoryDTO productCategoryDTO){

        boolean result = pmsProductCategoryService.update(productCategoryDTO);
        if(result){
            return CommonResult.success(result);
        }
        else {
            return CommonResult.failed();
        }

    }
    /**
     *  根据id获取商品分类
     *  return request({
     *     url:'/productCategory/'+id,
     *     method:'get',
     *   })
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public CommonResult<PmsProductCategory> getById(@PathVariable Long id){

        PmsProductCategory productCategory = pmsProductCategoryService.getById(id);
        return CommonResult.success(productCategory);
    }

    /**
     *   获取商品一级分类和二级分类的下拉级联数据
     *   url:'/productCategory/list/withChildren',
     *     method:'get'
     */
    @RequestMapping("/list/withChildren")
    public  CommonResult getWithChildren(){
        List<ProductCateChildrenDTO> list= pmsProductCategoryService.getWithChildren();

        return CommonResult.success(list);
    }
}

