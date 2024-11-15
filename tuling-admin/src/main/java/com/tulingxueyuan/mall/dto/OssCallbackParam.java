package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*** @author wkx * @date 2024年11月12日 16:58
 *  oss上传成功后的回调参数
 * */
@Data
public class OssCallbackParam {
    @ApiModelProperty("请求的回调地址")
    private String callbackUrl;
    @ApiModelProperty("回调是传入request中的参数")
    private String callbackBody;
    @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;

    //省略了所有getter,setter方法
}
