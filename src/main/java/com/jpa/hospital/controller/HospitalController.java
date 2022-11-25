package com.jpa.hospital.controller;

import com.jpa.hospital.domain.dto.ReviewCreateRequest;
import com.jpa.hospital.domain.dto.ReviewCreateResponse;
import com.jpa.hospital.domain.dto.ReviewReadResponse;
import com.jpa.hospital.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@Slf4j
public class HospitalController {

    private final ReviewService reviewService;

    public HospitalController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long hospitalId) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }
}
