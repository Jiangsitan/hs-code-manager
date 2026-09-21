// LucasJay 17302732991
package com.hscode.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hscode.manager.dto.HsCodeCreateDTO;
import com.hscode.manager.dto.HsCodeUpdateDTO;
import com.hscode.manager.entity.HsCode;
import com.hscode.manager.mapper.HsCodeMapper;
import com.hscode.manager.service.HsCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * HS编码Service实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HsCodeServiceImpl extends ServiceImpl<HsCodeMapper, HsCode> implements HsCodeService {

    @Override
    public IPage<HsCode> listPage(Integer page, Integer size, String keyword) {
        Page<HsCode> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<HsCode> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(keyword)) {
            wrapper.like(HsCode::getHsCode, keyword)
                    .or().like(HsCode::getProductName, keyword)
                    .or().like(HsCode::getProductDescription, keyword)
                    .or().like(HsCode::getProductNameCn, keyword)
                    .or().like(HsCode::getProductNameEn, keyword);
        }

        wrapper.orderByDesc(HsCode::getCreatedAt);
        return baseMapper.selectPage(pageParam, wrapper);
    }

    @Override
    public HsCode getDetail(String hsCode) {
        return getById(hsCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HsCode create(HsCodeCreateDTO createDTO) {
        // 检查是否已存在
        HsCode existing = getById(createDTO.getHsCode());
        if (existing != null) {
            log.warn("HS编码已存在: {}", createDTO.getHsCode());
            throw new RuntimeException("HS编码已存在: " + createDTO.getHsCode());
        }

        HsCode hsCode = HsCode.builder()
                .hsCode(createDTO.getHsCode())
                .productName(createDTO.getProductName())
                .productDescription(createDTO.getProductDescription())
                .productNameCn(createDTO.getProductNameCn())
                .productNameEn(createDTO.getProductNameEn())
                .declarationElements(createDTO.getDeclarationElements())
                .firstUnit(createDTO.getFirstUnit())
                .secondUnit(createDTO.getSecondUnit())
                .exportTaxRebateRate(createDTO.getExportTaxRebateRate())
                .mfnRate(createDTO.getMfnRate())
                .commonRate(createDTO.getCommonRate())
                .importVat(createDTO.getImportVat())
                .importConsumptionTax(createDTO.getImportConsumptionTax())
                .classificationReference(createDTO.getClassificationReference())
                .customsSupervision(createDTO.getCustomsSupervision())
                .inspectionQuarantine(createDTO.getInspectionQuarantine())
                .dataSource(StringUtils.hasText(createDTO.getDataSource()) ? createDTO.getDataSource() : "manual")
                .build();

        save(hsCode);
        log.info("手动添加HS编码成功: {}", hsCode.getHsCode());
        return hsCode;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HsCode update(String hsCode, HsCodeUpdateDTO updateDTO) {
        HsCode existing = getById(hsCode);
        if (existing == null) {
            log.warn("HS编码不存在: {}", hsCode);
            throw new RuntimeException("HS编码不存在: " + hsCode);
        }

        // 更新字段（仅更新非空字段）
        if (StringUtils.hasText(updateDTO.getProductName())) {
            existing.setProductName(updateDTO.getProductName());
        }
        if (StringUtils.hasText(updateDTO.getProductDescription())) {
            existing.setProductDescription(updateDTO.getProductDescription());
        }
        if (StringUtils.hasText(updateDTO.getProductNameCn())) {
            existing.setProductNameCn(updateDTO.getProductNameCn());
        }
        if (StringUtils.hasText(updateDTO.getProductNameEn())) {
            existing.setProductNameEn(updateDTO.getProductNameEn());
        }
        if (StringUtils.hasText(updateDTO.getDeclarationElements())) {
            existing.setDeclarationElements(updateDTO.getDeclarationElements());
        }
        if (StringUtils.hasText(updateDTO.getFirstUnit())) {
            existing.setFirstUnit(updateDTO.getFirstUnit());
        }
        if (StringUtils.hasText(updateDTO.getSecondUnit())) {
            existing.setSecondUnit(updateDTO.getSecondUnit());
        }
        if (StringUtils.hasText(updateDTO.getExportTaxRebateRate())) {
            existing.setExportTaxRebateRate(updateDTO.getExportTaxRebateRate());
        }
        if (StringUtils.hasText(updateDTO.getMfnRate())) {
            existing.setMfnRate(updateDTO.getMfnRate());
        }
        if (StringUtils.hasText(updateDTO.getCommonRate())) {
            existing.setCommonRate(updateDTO.getCommonRate());
        }
        if (StringUtils.hasText(updateDTO.getImportVat())) {
            existing.setImportVat(updateDTO.getImportVat());
        }
        if (StringUtils.hasText(updateDTO.getImportConsumptionTax())) {
            existing.setImportConsumptionTax(updateDTO.getImportConsumptionTax());
        }
        if (StringUtils.hasText(updateDTO.getClassificationReference())) {
            existing.setClassificationReference(updateDTO.getClassificationReference());
        }
        if (StringUtils.hasText(updateDTO.getCustomsSupervision())) {
            existing.setCustomsSupervision(updateDTO.getCustomsSupervision());
        }
        if (StringUtils.hasText(updateDTO.getInspectionQuarantine())) {
            existing.setInspectionQuarantine(updateDTO.getInspectionQuarantine());
        }
        if (StringUtils.hasText(updateDTO.getDataSource())) {
            existing.setDataSource(updateDTO.getDataSource());
        }

        updateById(existing);
        log.info("更新HS编码成功: {}", hsCode);
        return existing;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String hsCode) {
        HsCode existing = getById(hsCode);
        if (existing == null) {
            log.warn("HS编码不存在: {}", hsCode);
            throw new RuntimeException("HS编码不存在: " + hsCode);
        }

        boolean result = removeById(hsCode);
        log.info("删除HS编码: {}, 结果: {}", hsCode, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HsCode syncHsCode(HsCode hsCode) {
        HsCode existing = getById(hsCode.getHsCode());

        if (existing != null) {
            // 已存在，用完整数据更新（覆盖手动添加的数据）
            if (StringUtils.hasText(hsCode.getProductName())) {
                existing.setProductName(hsCode.getProductName());
            }
            if (StringUtils.hasText(hsCode.getProductDescription())) {
                existing.setProductDescription(hsCode.getProductDescription());
            }
            if (StringUtils.hasText(hsCode.getProductNameCn())) {
                existing.setProductNameCn(hsCode.getProductNameCn());
            }
            if (StringUtils.hasText(hsCode.getProductNameEn())) {
                existing.setProductNameEn(hsCode.getProductNameEn());
            }
            if (StringUtils.hasText(hsCode.getDeclarationElements())) {
                existing.setDeclarationElements(hsCode.getDeclarationElements());
            }
            if (StringUtils.hasText(hsCode.getFirstUnit())) {
                existing.setFirstUnit(hsCode.getFirstUnit());
            }
            if (StringUtils.hasText(hsCode.getSecondUnit())) {
                existing.setSecondUnit(hsCode.getSecondUnit());
            }
            if (StringUtils.hasText(hsCode.getExportTaxRebateRate())) {
                existing.setExportTaxRebateRate(hsCode.getExportTaxRebateRate());
            }
            if (StringUtils.hasText(hsCode.getMfnRate())) {
                existing.setMfnRate(hsCode.getMfnRate());
            }
            if (StringUtils.hasText(hsCode.getCommonRate())) {
                existing.setCommonRate(hsCode.getCommonRate());
            }
            if (StringUtils.hasText(hsCode.getImportVat())) {
                existing.setImportVat(hsCode.getImportVat());
            }
            if (StringUtils.hasText(hsCode.getImportConsumptionTax())) {
                existing.setImportConsumptionTax(hsCode.getImportConsumptionTax());
            }
            if (StringUtils.hasText(hsCode.getClassificationReference())) {
                existing.setClassificationReference(hsCode.getClassificationReference());
            }
            if (StringUtils.hasText(hsCode.getCustomsSupervision())) {
                existing.setCustomsSupervision(hsCode.getCustomsSupervision());
            }
            if (StringUtils.hasText(hsCode.getInspectionQuarantine())) {
                existing.setInspectionQuarantine(hsCode.getInspectionQuarantine());
            }
            existing.setDataSource("sync");

            updateById(existing);
            log.info("同步更新HS编码: {}", hsCode.getHsCode());
            return existing;
        } else {
            // 不存在，新增
            hsCode.setDataSource("sync");
            save(hsCode);
            log.info("同步新增HS编码: {}", hsCode.getHsCode());
            return hsCode;
        }
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCount", count());

        // 按来源统计
        LambdaQueryWrapper<HsCode> syncWrapper = new LambdaQueryWrapper<>();
        syncWrapper.eq(HsCode::getDataSource, "sync");
        stats.put("syncCount", count(syncWrapper));

        LambdaQueryWrapper<HsCode> manualWrapper = new LambdaQueryWrapper<>();
        manualWrapper.eq(HsCode::getDataSource, "manual");
        stats.put("manualCount", count(manualWrapper));

        return stats;
    }
}
