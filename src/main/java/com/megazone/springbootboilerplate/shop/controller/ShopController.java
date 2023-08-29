package com.megazone.springbootboilerplate.shop.controller;

import com.megazone.springbootboilerplate.common.dto.CommonResponse;
import com.megazone.springbootboilerplate.common.dto.CommonResponseType;
import com.megazone.springbootboilerplate.shop.service.ShopReadService;
import com.megazone.springbootboilerplate.shop.service.ShopWriteService;
import com.megazone.springbootboilerplate.shop.service.dto.request.ShopCreateRequest;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopWriteService shopWriteService;
    private final ShopReadService shopReadService;

    @PostMapping("/shops")
    public ResponseEntity<CommonResponse<ShopResponse>> create(@RequestBody @Validated ShopCreateRequest request) {
        ShopResponse shopResponse = shopWriteService.create(request);
        return CommonResponse.created(shopResponse, "/shops/" + shopResponse.id());
    }

    @GetMapping("/shops")
    public ResponseEntity<CommonResponse<List<ShopResponse>>> findAll() {
        return CommonResponse.ok(shopReadService.findAll());
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<CommonResponse<ShopResponse>> findOne(@PathVariable Long shopId) {
        return CommonResponse.ok(shopReadService.findById(shopId));
    }

    @PutMapping("/shops/{shopId}:{shopAction}")
    public ResponseEntity<CommonResponse<ShopResponse>> update(@PathVariable Long shopId, @PathVariable String shopAction) {
        return CommonResponse.withData(shopWriteService.update(shopId, shopAction), CommonResponseType.UPDATE);
    }
}
