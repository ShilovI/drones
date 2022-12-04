package com.shilovi.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shilovi.drones.utilities.JsonHelper;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicationDto {

    @JsonProperty("code")
    @Pattern(regexp = "[A-Za-z0-9,_]+")
    private String code;

    @JsonProperty("name")
    @Pattern(regexp = "[A-Z0-9_]+")
    private String name;

    @JsonProperty("weight")
    private Long weight;

    @Override
    public String toString() {
        return JsonHelper.getStringFromObject(this);
    }

}
