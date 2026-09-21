// LucasJay 17302732991
package com.hscode.manager.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * HS编码实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("hs_code")
@Schema(description = "HS编码实体")
public class HsCode {

    @Schema(description = "归类编号", example = "0101.21")
    @TableId(value = "hs_code", type = IdType.INPUT)
    private String hsCode;

    @Schema(description = "商品名称", example = "改良种用马")
    @TableField("product_name")
    private String productName;

    @Schema(description = "商品描述", example = "Pure-bred breeding horses")
    @TableField("product_description")
    private String productDescription;

    @Schema(description = "报关品名（中文）", example = "改良种用马")
    @TableField("product_name_cn")
    private String productNameCn;

    @Schema(description = "报关品名（英文）", example = "Pure-bred breeding horses")
    @TableField("product_name_en")
    private String productNameEn;

    @Schema(description = "申报要素")
    @TableField("declaration_elements")
    private String declarationElements;

    @Schema(description = "法定第一单位", example = "头")
    @TableField("first_unit")
    private String firstUnit;

    @Schema(description = "法定第二单位", example = "千克")
    @TableField("second_unit")
    private String secondUnit;

    @Schema(description = "出口退税率", example = "0%")
    @TableField("export_tax_rebate_rate")
    private String exportTaxRebateRate;

    @Schema(description = "最惠国税率", example = "10%")
    @TableField("mfn_rate")
    private String mfnRate;

    @Schema(description = "普通税率", example = "30%")
    @TableField("common_rate")
    private String commonRate;

    @Schema(description = "进口增值税", example = "13%")
    @TableField("import_vat")
    private String importVat;

    @Schema(description = "进口消费税", example = "0%")
    @TableField("import_consumption_tax")
    private String importConsumptionTax;

    @Schema(description = "归类参考（常见报关品名及占比）")
    @TableField("classification_reference")
    private String classificationReference;

    @Schema(description = "海关监管条件")
    @TableField("customs_supervision")
    private String customsSupervision;

    @Schema(description = "检验检疫")
    @TableField("inspection_quarantine")
    private String inspectionQuarantine;

    @Schema(description = "来源", example = "manual")
    @TableField("data_source")
    private String dataSource;

    @Schema(description = "数据来源站点（仅远程查询时返回）", example = "hsguilei")
    @TableField(exist = false)
    private String sourceSite;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
