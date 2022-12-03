package com.shilovi.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shilovi.drones.model.base.DroneModel;
import com.shilovi.drones.model.base.DroneState;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DroneDto {

    @JsonProperty("serialNumber")
    private String serialNumber;

    @JsonProperty("weightLimit")
    private Long weightLimit;

    @JsonProperty("model")
    private DroneModel model;

    @JsonProperty("state")
    private DroneState state;

}
