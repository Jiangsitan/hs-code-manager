// LucasJay 17302732991
package com.hscode.manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HS编码更新请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "HS编码更新请求")
public class HsCodeUpdateDTO {

    @Schema(description = "商品名称", example = "改良种用马")
    private String productName;

    @Schema(description = "商品描述", example = "Pure-bred breeding horses")
    private String productDescription;

    @Schema(description = "报关品名（中文）", example = "改良种用马")
    private String productNameCn;

    @Schema(description = "报关品名（英文）", example = "Pure-bred breeding horses")
    private String productNameEn;

    @Schema(description = "申报要素")
    private String declarationElements;

    @Schema(description = "法定第一单位", example = "头")
    private String firstUnit;

    @Schema(description = "法定第二单位", example = "千克")
    private String secondUnit;

    @Schema(description = "出口退税率", example = "0%")
    private String exportTaxRebateRate;

    @Schema(description = "最惠国税率", example = "10%")
    private String mfnRate;

    @Schema(description = "普通税率", example = "30%")
    private String commonRate;

    @Schema(description = "进口增值税", example = "13%")
    private String importVat;

    @Schema(description = "进口消费税", example = "0%")
    private String importConsumptionTax;

    @Schema(description = "归类参考（常见报关品名及占比）")
    private String classificationReference;

    @Schema(description = "海关监管条件")
    private String customsSupervision;

    @Schema(description = "检验检疫")
    private String inspectionQuarantine;

    @Schema(description = "来源", example = "sync")
    private String dataSource;
}
