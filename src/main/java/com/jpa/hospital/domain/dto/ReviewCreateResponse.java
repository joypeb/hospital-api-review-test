package com.jpa.hospital.domain.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewCreateResponse {

    private String userName;
    private String title;
    private String content;
    private String message;
}
