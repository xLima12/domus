package br.com.codenoir.domus.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_offer_property")
@Data
public class OfferPropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime date;

    @NotBlank(message = "Type Offer should not be blank")
    @NotNull
    private String typeOffer;

    @OneToOne()
    @Column(name = "property_id")
    private PropertyEntity propertyId;

    @Min(value = 1, message = "Price should not be less than 1 (one).")
    private BigDecimal price;

}
