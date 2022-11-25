package com.jpa.hospital.controller;

import com.jpa.hospital.domain.Review;
import com.jpa.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@Slf4j
@RequiredArgsConstructor //필요한 argument를 넣어줌
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<Review> get(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        return ResponseEntity.ok().body(review);
    }
}
