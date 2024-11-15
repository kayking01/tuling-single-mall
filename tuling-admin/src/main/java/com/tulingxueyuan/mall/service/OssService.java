package com.tulingxueyuan.mall.service;

import com.tulingxueyuan.mall.dto.OssPolicyResult;

/*** @author wkx * @date 2024年11月12日 17:01 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
//    OssCallbackResult callback(HttpServletRequest request);
}
