// LucasJay 17302732991
package com.hscode.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hscode.manager.dto.HsCodeCreateDTO;
import com.hscode.manager.dto.HsCodeUpdateDTO;
import com.hscode.manager.entity.HsCode;

import java.util.Map;

/**
 * HS编码Service接口
 */
public interface HsCodeService extends IService<HsCode> {

    /**
     * 分页查询HS编码列表
     * @param page 页码
     * @param size 每页大小
     * @param keyword 搜索关键词（可选）
     * @return 分页结果
     */
    IPage<HsCode> listPage(Integer page, Integer size, String keyword);

    /**
     * 根据归类编号查询详情
     * @param hsCode 归类编号
     * @return HS编码详情
     */
    HsCode getDetail(String hsCode);

    /**
     * 新增HS编码（手动添加）
     * @param createDTO 创建请求
     * @return 新增的HS编码
     */
    HsCode create(HsCodeCreateDTO createDTO);

    /**
     * 更新HS编码
     * @param hsCode 归类编号
     * @param updateDTO 更新请求
     * @return 更新后的HS编码
     */
    HsCode update(String hsCode, HsCodeUpdateDTO updateDTO);

    /**
     * 删除HS编码
     * @param hsCode 归类编号
     * @return 是否删除成功
     */
    boolean delete(String hsCode);

    /**
     * 同步HS编码（远程数据同步）
     * @param hsCode HS编码数据
     * @return 同步结果
     */
    HsCode syncHsCode(HsCode hsCode);

    /**
     * 获取统计信息
     * @return 统计数据
     */
    Map<String, Object> getStats();
}
