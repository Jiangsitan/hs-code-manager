# LucasJay 17302732991
-- HS编码数据库建表脚本
CREATE DATABASE IF NOT EXISTS hs_code_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE hs_code_db;

-- HS编码表
DROP TABLE IF EXISTS `hs_code`;
CREATE TABLE `hs_code` (
    `hs_code` VARCHAR(50) NOT NULL COMMENT '归类编号',
    `product_name` VARCHAR(500) DEFAULT NULL COMMENT '商品名称',
    `product_description` TEXT COMMENT '商品描述',
    `product_name_cn` VARCHAR(500) DEFAULT NULL COMMENT '报关品名（中文）',
    `product_name_en` VARCHAR(500) DEFAULT NULL COMMENT '报关品名（英文）',
    `declaration_elements` TEXT COMMENT '申报要素',
    `first_unit` VARCHAR(50) DEFAULT NULL COMMENT '法定第一单位',
    `second_unit` VARCHAR(50) DEFAULT NULL COMMENT '法定第二单位',
    `export_tax_rebate_rate` VARCHAR(20) DEFAULT NULL COMMENT '出口退税率',
    `mfn_rate` VARCHAR(20) DEFAULT NULL COMMENT '最惠国税率',
    `common_rate` VARCHAR(20) DEFAULT NULL COMMENT '普通税率',
    `import_vat` VARCHAR(20) DEFAULT NULL COMMENT '进口增值税',
    `import_consumption_tax` VARCHAR(20) DEFAULT NULL COMMENT '进口消费税',
    `classification_reference` TEXT COMMENT '归类参考（常见报关品名及占比）',
    `customs_supervision` TEXT COMMENT '海关监管条件',
    `inspection_quarantine` TEXT COMMENT '检验检疫',
    `data_source` VARCHAR(20) NOT NULL DEFAULT 'manual' COMMENT '来源：sync/manual',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`hs_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='HS编码表';

-- 索引
CREATE INDEX `idx_product_name` ON `hs_code`(`product_name`);
CREATE INDEX `idx_product_name_cn` ON `hs_code`(`product_name_cn`);
CREATE INDEX `idx_product_name_en` ON `hs_code`(`product_name_en`);
CREATE INDEX `idx_data_source` ON `hs_code`(`data_source`);
