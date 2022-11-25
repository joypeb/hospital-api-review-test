package com.jpa.hospital.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReadRequest {
    private Long id;
    private String title;
    private String content;
    private String patientName;
    private String hospitalName;
}
