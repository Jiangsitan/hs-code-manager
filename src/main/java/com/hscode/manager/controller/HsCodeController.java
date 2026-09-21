// LucasJay 17302732991
package com.hscode.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hscode.manager.dto.ApiResponse;
import com.hscode.manager.dto.HsCodeCreateDTO;
import com.hscode.manager.dto.HsCodeUpdateDTO;
import com.hscode.manager.entity.HsCode;
import com.hscode.manager.service.HsCodeService;
import com.hscode.manager.service.RemoteQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * HS编码控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/hs-codes")
@RequiredArgsConstructor
@Tag(name = "HS编码管理", description = "HS编码的增删改查接口")
public class HsCodeController {

    private final HsCodeService hsCodeService;
    private final RemoteQueryService remoteQueryService;

    @GetMapping("/remote-search")
    @Operation(summary = "按品名搜索HS编码", description = "从远程网站按报关品名关键词搜索HS编码列表")
    public ApiResponse<List<HsCode>> remoteSearch(
            @Parameter(description = "品名关键词", required = true)
            @RequestParam String keyword) {
        try {
            List<HsCode> result = remoteQueryService.searchByName(keyword);
            return ApiResponse.success(result);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

    @GetMapping("/remote-query")
    @Operation(summary = "远程查询HS编码详情", description = "根据HS编码从远程网站查询完整详情")
    public ApiResponse<HsCode> remoteQuery(
            @Parameter(description = "归类编号", required = true)
            @RequestParam String hsCode) {
        try {
            HsCode result = remoteQueryService.queryDetail(hsCode);
            return ApiResponse.success(result);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "分页查询HS编码列表", description = "支持关键词搜索归类编号、中文名、英文名")
    public ApiResponse<IPage<HsCode>> list(
            @Parameter(description = "页码", example = "1")
            @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "搜索关键词")
            @RequestParam(required = false) String keyword) {
        IPage<HsCode> result = hsCodeService.listPage(page, size, keyword);
        return ApiResponse.success(result);
    }

    @GetMapping("/{hsCode}")
    @Operation(summary = "查询HS编码详情", description = "根据归类编号查询详情")
    public ApiResponse<HsCode> detail(
            @Parameter(description = "归类编号", required = true)
            @PathVariable String hsCode) {
        HsCode result = hsCodeService.getDetail(hsCode);
        if (result == null) {
            return ApiResponse.error(404, "HS编码不存在: " + hsCode);
        }
        return ApiResponse.success(result);
    }

    @PostMapping
    @Operation(summary = "新增HS编码", description = "手动添加HS编码，至少需要归类编号")
    public ApiResponse<HsCode> create(@Valid @RequestBody HsCodeCreateDTO createDTO) {
        try {
            HsCode result = hsCodeService.create(createDTO);
            return ApiResponse.success("新增成功", result);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

    @PutMapping("/{hsCode}")
    @Operation(summary = "更新HS编码", description = "更新指定归类编号的HS编码信息")
    public ApiResponse<HsCode> update(
            @Parameter(description = "归类编号", required = true)
            @PathVariable String hsCode,
            @RequestBody HsCodeUpdateDTO updateDTO) {
        try {
            HsCode result = hsCodeService.update(hsCode, updateDTO);
            return ApiResponse.success("更新成功", result);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

    @DeleteMapping("/{hsCode}")
    @Operation(summary = "删除HS编码", description = "根据归类编号删除HS编码")
    public ApiResponse<Void> delete(
            @Parameter(description = "归类编号", required = true)
            @PathVariable String hsCode) {
        try {
            hsCodeService.delete(hsCode);
            return ApiResponse.success("删除成功", null);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

    @GetMapping("/stats")
    @Operation(summary = "获取统计信息", description = "获取HS编码数量统计")
    public ApiResponse<Map<String, Object>> stats() {
        Map<String, Object> result = hsCodeService.getStats();
        return ApiResponse.success(result);
    }

    @PostMapping("/sync")
    @Operation(summary = "同步HS编码", description = "从远程同步HS编码数据，已存在则更新")
    public ApiResponse<HsCode> sync(@RequestBody HsCode hsCode) {
        HsCode result = hsCodeService.syncHsCode(hsCode);
        return ApiResponse.success("同步成功", result);
    }
}
