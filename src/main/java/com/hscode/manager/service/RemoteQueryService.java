// LucasJay 17302732991
package com.hscode.manager.service;

import com.hscode.manager.entity.HsCode;
import com.hscode.sdk.HsCodeClient;
import com.hscode.sdk.dto.HsCodeSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 远程查询服务（薄代理层）
 * <p>
 * 委托 hs-code-sdk 执行实际的远程查询，
 * 负责将 SDK 的 HsCodeSearchResult 转换为本项目的 HsCode 实体。
 */
@Slf4j
@Service
public class RemoteQueryService {

    private final HsCodeClient client = new HsCodeClient();

    /**
     * 按关键词搜索HS编码（双源合并）
     *
     * @param keyword 搜索关键词
     * @return 合并后的HS编码实体列表
     */
    public List<HsCode> searchByName(String keyword) {
        List<HsCodeSearchResult> results = client.search(keyword);
        return results.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    /**
     * 按HS编码查询详情
     *
     * @param hsCode HS编码（10位数字）
     * @return HS编码详情实体
     */
    public HsCode queryDetail(String hsCode) {
        HsCodeSearchResult result = client.queryDetail(hsCode);
        return convertToEntity(result);
    }

    /**
     * SDK DTO → 本项目实体
     */
    private HsCode convertToEntity(HsCodeSearchResult r) {
        if (r == null) {
            return new HsCode();
        }
        HsCode entity = new HsCode();
        entity.setHsCode(r.getHsCode());
        entity.setProductName(r.getProductName());
        entity.setProductDescription(r.getProductDescription());
        entity.setFirstUnit(r.getFirstUnit());
        entity.setSecondUnit(r.getSecondUnit());
        entity.setExportTaxRebateRate(r.getExportTaxRebateRate());
        entity.setMfnRate(r.getMfnRate());
        entity.setCommonRate(r.getCommonRate());
        entity.setImportVat(r.getImportVat());
        entity.setImportConsumptionTax(r.getImportConsumptionTax());
        entity.setDeclarationElements(r.getDeclarationElements());
        entity.setCustomsSupervision(r.getCustomsSupervision());
        entity.setInspectionQuarantine(r.getInspectionQuarantine());
        entity.setClassificationReference(r.getClassificationReference());
        entity.setSourceSite(r.getSourceSite());
        entity.setDataSource("sync");
        return entity;
    }
}
