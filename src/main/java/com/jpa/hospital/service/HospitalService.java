package com.jpa.hospital.service;

import com.jpa.hospital.controller.HospitalController;
import com.jpa.hospital.domain.Hospital;
import com.jpa.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    public Hospital findById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id가 없습니다"));
        return hospital;
    }
}
