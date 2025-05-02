package br.com.codenoir.domus.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OfferPropertyRequestDTO {

    private LocalDateTime date;

    @NotNull(message = "Type Offer should not be null")
    private int offerType;

    @NotNull(message = "Property id should not be null")
    private String property_id;

    @Size(min = 1, message = "Price should not be less than 1 (one).")
    private BigDecimal price;

}
