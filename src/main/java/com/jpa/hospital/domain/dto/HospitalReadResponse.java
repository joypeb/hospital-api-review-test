package com.jpa.hospital.domain.dto;

import com.jpa.hospital.domain.Hospital;
import com.jpa.hospital.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HospitalReadResponse {

    private Long id;
    private String hospitalName;
    private String roadNameAddress;
    private List<ReviewReadResponse> reviews;

    public static HospitalReadResponse fromEntity(Hospital hospital) {
        return HospitalReadResponse.builder()
                .id(hospital.getId())
                .hospitalName(hospital.getHospitalName())
                .roadNameAddress(hospital.getRoadNameAddress())
                .reviews(hospital.getReviews().stream().map(review -> ReviewReadResponse.fromEntity(review)).collect(Collectors.toList()))
                .build();
    }
}
