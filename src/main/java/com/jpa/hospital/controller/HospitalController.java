package com.jpa.hospital.controller;

import com.jpa.hospital.domain.Hospital;
import com.jpa.hospital.domain.dto.HospitalReadResponse;
import com.jpa.hospital.domain.dto.ReviewCreateRequest;
import com.jpa.hospital.domain.dto.ReviewCreateResponse;
import com.jpa.hospital.domain.dto.ReviewReadResponse;
import com.jpa.hospital.service.HospitalService;
import com.jpa.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@Slf4j
@RequiredArgsConstructor
public class HospitalController {

    private final ReviewService reviewService;
    private final HospitalService hospitalService;

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long hospitalId) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadResponse> findById(@PathVariable Long id) {
        Hospital hospital = hospitalService.findById(id);
        return ResponseEntity.ok().body(HospitalReadResponse.fromEntity(hospital));
    }
}
