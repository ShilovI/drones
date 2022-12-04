package com.shilovi.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shilovi.drones.utilities.JsonHelper;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatteryLevelDto {

    @NotEmpty
    @Size(max = 100)
    @JsonProperty("serialNumber")
    private String serialNumber;

    @JsonProperty("batteryLevel")
    private Integer batteryLevel;

    @Override
    public String toString() {
        return JsonHelper.getStringFromObject(this);
    }

}
