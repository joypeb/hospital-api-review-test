package com.jpa.hospital.service;

import com.jpa.hospital.domain.Hospital;
import com.jpa.hospital.domain.Review;
import com.jpa.hospital.domain.dto.ReviewCreateRequest;
import com.jpa.hospital.domain.dto.ReviewCreateResponse;
import com.jpa.hospital.domain.dto.ReviewReadResponse;
import com.jpa.hospital.repository.HospitalRepository;
import com.jpa.hospital.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReviewService {

    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public ReviewCreateResponse createReview(ReviewCreateRequest dto) {
        Optional<Hospital> hospital = hospitalRepository.findById(dto.getHospitalId());
        Review review = Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .patientName(dto.getUserName())
                .hospital(hospital.get())
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewCreateResponse.builder()
                .userName(savedReview.getPatientName())
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .message("리뷰 등록 성공")
                .build();
    }

    public Review getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 id가 없습니다"));
        log.info("리뷰 데이터 : " + review.toString());
        return review;
    }

    public List<ReviewReadResponse> findAllByHospitalId(Long hospitalId) {
        List<ReviewReadResponse> reviews = reviewRepository.findByHospitalId(hospitalId).stream().map(
                review -> ReviewReadResponse.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .content(review.getContent())
                        .patientName(review.getPatientName())
                        .hospitalName(review.getHospital().getHospitalName())
                        .build()
        ).collect(Collectors.toList());
        return reviews;
    }
}
