package br.com.codenoir.domus.application.offerProperty.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfferPropertyRequestDTO {

    @NotNull(message = "Type Offer should not be null")
    private int offerType;

    @NotNull(message = "Property id should not be null")
    private String property_id;

    @DecimalMin(value = "0.01", message = "Price should not be less than 0.01.")
    private BigDecimal price;

}
