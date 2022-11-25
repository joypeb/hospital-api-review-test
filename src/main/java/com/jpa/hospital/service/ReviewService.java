package com.jpa.hospital.service;

import com.jpa.hospital.domain.Hospital;
import com.jpa.hospital.domain.Review;
import com.jpa.hospital.domain.dto.ReviewCreateRequest;
import com.jpa.hospital.domain.dto.ReviewCreateResponse;
import com.jpa.hospital.repository.HospitalRepository;
import com.jpa.hospital.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
