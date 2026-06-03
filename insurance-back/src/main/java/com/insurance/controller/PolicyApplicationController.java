package com.insurance.controller;

import com.insurance.common.ApiResponse;
import com.insurance.entity.PolicyApplication;
import com.insurance.service.PolicyApplicationService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import com.insurance.dto.PolicyApplicationReqDTO;
import com.insurance.dto.PolicyApplicationUpdateDTO;

// ResponseEntity → 包裝 HTTP 狀態碼（.ok()=200, .notFound()=404, .badRequest()=400, .internalServerError()=500）
//   └── ApiResponse → 包裝業務狀態碼 { code, message, data }
//         └── 實際資料型態（List<PolicyApplication> / PolicyApplication / Boolean）
@RestController
@RequestMapping("/api/policy-application")
public class PolicyApplicationController {

    @Autowired // Spring 自動注入 PolicyApplicationServiceImpl，不需要自己 new
    private PolicyApplicationService policyApplicationService;

    @GetMapping("/list")
    @Operation(summary = "查詢所有投保申請")
    public ResponseEntity<ApiResponse<List<PolicyApplication>>> list() {
        // policyApplicationService.list() → 查資料庫，回傳 List<PolicyApplication>
        // ApiResponse.success() → 包裝成 { code: 200, message: "成功", data: [...] }
        // ResponseEntity.ok() → 設定 HTTP 200 回傳給前端
        return ResponseEntity.ok(ApiResponse.success(policyApplicationService.list()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "查詢單筆投保申請")
    public ResponseEntity<ApiResponse<PolicyApplication>> getById(@PathVariable Integer id) {
        // 1. policyApplicationService.getById(id) → 呼叫 Service 查詢單筆投保申請
        // 2. ApiResponse.success() → 包裝成 { code: 200, message: "成功", data: {...} }
        // 3. ResponseEntity.ok() → 設定 HTTP 200 回傳給前端
        PolicyApplication result = policyApplicationService.getById(id);
        // 查不到資料，拋出例外讓 GlobalExceptionHandler 攔截回傳 404
        if (result == null) {
            throw new NoSuchElementException("找不到 id：" + id);
        }
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping
    @Operation(summary = "新增投保申請")
    public ResponseEntity<ApiResponse<Integer>> save(@RequestBody PolicyApplicationReqDTO policyApplicationDTO) {
        // 1. DTO → Entity 轉換（只包含前端能傳的欄位，applicationId 等由 ServiceImpl 自動產生）
        PolicyApplication policyApplication = new PolicyApplication();
        policyApplication.setNationalId(policyApplicationDTO.getNationalId());
        policyApplication.setApplicantName(policyApplicationDTO.getApplicantName());
        policyApplication.setApplicantGender(policyApplicationDTO.getApplicantGender());
        policyApplication.setApplicantBirthdate(policyApplicationDTO.getApplicantBirthdate());
        policyApplication.setRelationshipToInsured(policyApplicationDTO.getRelationshipToInsured());
        policyApplication.setProductCode(policyApplicationDTO.getProductCode());
        policyApplication.setSumInsured(policyApplicationDTO.getSumInsured());
        policyApplication.setAnnualPremium(policyApplicationDTO.getAnnualPremium());
        policyApplication.setContactPhone(policyApplicationDTO.getContactPhone());
        policyApplication.setCreatedBy(policyApplicationDTO.getCreatedBy());

        PolicyApplication result = policyApplicationService.createApplication(policyApplication);
        return ResponseEntity.ok(ApiResponse.success(result.getApplicationId()));
    }// 新增成功 200失敗500

    @PutMapping("/{id}")
    @Operation(summary = "修改投保申請")
    public ResponseEntity<ApiResponse<Boolean>> update(
            @PathVariable Integer id,
            @RequestBody PolicyApplicationUpdateDTO policyApplicationDTO) {

        // 1. DTO → Entity 轉換（applicationId 從 URL 取，其他欄位從 DTO 取）
        PolicyApplication policyApplication = new PolicyApplication();
        policyApplication.setApplicationId(id);
        policyApplication.setApplicationStatus(policyApplicationDTO.getStatus());
        // DTO 是給前端用的，名稱可以簡短清楚。Entity 要對應資料庫欄位名稱，所以用完整的欄位名稱
        policyApplication.setReviewedBy(policyApplicationDTO.getReviewedBy());
        policyApplication.setRejectionReason(policyApplicationDTO.getRejectionReason());

        // 2. 呼叫自訂的 updateStatus，會自動設定 reviewTime
        policyApplicationService.updateStatus(policyApplication);

        // 3. 回傳 true 代表修改成功
        return ResponseEntity.ok(ApiResponse.success(true));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除投保申請")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Integer id) {
        // removeById 回傳 false 代表找不到資料，拋出例外讓 GlobalExceptionHandler 攔截回傳 404
        if (!policyApplicationService.removeById(id)) {
            throw new NoSuchElementException("找不到 id：" + id);
        }
        // 回傳 true 代表刪除成功
        return ResponseEntity.ok(ApiResponse.success(true));
    }

}