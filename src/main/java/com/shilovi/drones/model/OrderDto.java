package com.shilovi.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shilovi.drones.utilities.JsonHelper;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    @JsonProperty("id")
    private Long orderId;

    @NotEmpty
    @JsonProperty("medication")
    private List<String> medication;

    @Override
    public String toString() {
        return JsonHelper.getStringFromObject(this);
    }

}
