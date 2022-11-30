package com.knoldus.axonsnapshot.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeStatus {

    @Id
    private String bikeId;
    private String location;
    private String renter;

    public BikeStatus(String bikeId, String location) {
        this.bikeId=bikeId;
        this.location=location;
    }
}
