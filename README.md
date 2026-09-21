<!-- LucasJay 17302732991 -->
# HS编码管理系统

HS编码（归类编号）管理系统，提供HS编码的增删改查功能，支持手动添加和远程数据同步。

## 技术栈

- **后端**: Spring Boot 3.2.0 + MyBatis-Plus 3.5.5
- **SDK**: hs-code-sdk 1.0.0（远程查询组件）
- **数据库**: MySQL 8.x
- **构建工具**: Maven
- **API文档**: SpringDoc OpenAPI (Swagger UI)

## 项目结构

```
hs-code-manager/
├── pom.xml
├── src/main/java/com/hscode/manager/
│   ├── HsCodeManagerApplication.java    # 启动类
│   ├── controller/
│   │   └── HsCodeController.java        # REST API
│   ├── service/
│   │   ├── HsCodeService.java           # 业务接口
│   │   ├── RemoteQueryService.java      # 远程查询代理（委托SDK）
│   │   └── impl/
│   │       └── HsCodeServiceImpl.java   # 业务实现
│   ├── mapper/
│   │   └── HsCodeMapper.java            # MyBatis-Plus Mapper
│   ├── entity/
│   │   └── HsCode.java                  # 实体类
│   ├── dto/
│   │   ├── HsCodeCreateDTO.java         # 创建请求
│   │   ├── HsCodeUpdateDTO.java         # 更新请求
│   │   └── ApiResponse.java             # 统一响应
│   └── config/
│       ├── MybatisPlusConfig.java       # MyBatis-Plus配置
│       └── GlobalExceptionHandler.java  # 全局异常处理
├── src/main/resources/
│   ├── application.yml                  # 应用配置
│   ├── static/index.html               # 前端页面
│   └── db/
│       └── schema.sql                   # 建表SQL
└── README.md
```

## 依赖关系

本项目依赖 `hs-code-sdk` 远程查询组件：

```xml
<dependency>
    <groupId>com.hscode</groupId>
    <artifactId>hs-code-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

SDK提供双源合并搜索能力（hsguilei.com + hsbianma.com），详见 [hs-code-sdk/README.md](../hs-code-sdk/README.md)。

## 数据库设计

### hs_code 表

| 字段 | 类型 | 说明 |
|------|------|------|
| hs_code | VARCHAR(50) PK | 归类编号 |
| product_name_cn | VARCHAR(500) | 报关品名（中文） |
| product_name_en | VARCHAR(500) | 报关品名（英文） |
| declaration_elements | TEXT | 申报要素 |
| first_unit | VARCHAR(50) | 法定第一单位 |
| second_unit | VARCHAR(50) | 法定第二单位 |
| export_tax_rebate_rate | VARCHAR(20) | 出口退税率 |
| customs_supervision | TEXT | 海关监管条件 |
| inspection_quarantine | TEXT | 检验检疫 |
| data_source | VARCHAR(20) | 来源：sync/manual |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

## 快速开始

### 1. 环境要求

- JDK 17+
- MySQL 8.x
- Maven 3.6+

### 2. 初始化数据库

```sql
-- 执行建表脚本
source src/main/resources/db/schema.sql
```

### 3. 修改数据库配置

编辑 `src/main/resources/application.yml`，修改数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hs_code_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: your_username
    password: your_password
```

### 4. 启动应用

```bash
mvn spring-boot:run
```

或打包后运行：

```bash
mvn clean package
java -jar target/hs-code-manager-1.0.0.jar
```

## API接口

启动后访问 Swagger UI: http://localhost:8080/swagger-ui.html

### 接口列表

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | /api/hs-codes | 分页查询列表 |
| GET | /api/hs-codes/{hsCode} | 查询详情 |
| POST | /api/hs-codes | 新增（手动添加） |
| PUT | /api/hs-codes/{hsCode} | 更新 |
| DELETE | /api/hs-codes/{hsCode} | 删除 |
| GET | /api/hs-codes/stats | 统计信息 |
| POST | /api/hs-codes/sync | 同步远程数据 |

### 请求示例

#### 新增HS编码

```json
POST /api/hs-codes
{
    "hsCode": "0101.21",
    "productNameCn": "改良种用马",
    "productNameEn": "Pure-bred breeding horses",
    "firstUnit": "头",
    "secondUnit": "千克"
}
```

#### 同步HS编码

```json
POST /api/hs-codes/sync
{
    "hsCode": "0101.21",
    "productNameCn": "改良种用马",
    "productNameEn": "Pure-bred breeding horses",
    "declarationElements": "1:品名;2:用途;3:品种",
    "firstUnit": "头",
    "secondUnit": "千克",
    "exportTaxRebateRate": "0%",
    "customsSupervision": "A",
    "inspectionQuarantine": "P/Q"
}
```

## 业务规则

1. **手动添加**: 只需提供归类编号，其他字段可选
2. **同步更新**: 当远程数据同步时，如果已存在手动添加的记录，用完整数据自动更新
3. **数据来源**: 通过 `data_source` 字段区分来源（sync=同步，manual=手动）

## 开发说明

### 本地开发

```bash
# 运行测试
mvn test

# 编译打包
mvn clean package

# 启动开发环境
mvn spring-boot:run
```

### 代码规范

- 使用 Lombok 简化代码
- 使用 MyBatis-Plus 简化数据库操作
- 使用 SpringDoc OpenAPI 生成API文档
- 使用统一的 ApiResponse 封装响应
