package br.com.codenoir.domus.application.offerProperty.entity;

import br.com.codenoir.domus.application.property.entity.PropertyEntity;
import br.com.codenoir.domus.application.shared.enums.OfferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
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

    @NotNull(message = "Type Offer should not be null")
    private OfferType offerType;

    @OneToOne()
    @JoinColumn(name = "property_id", insertable=false, updatable=false)
    private PropertyEntity propertyId;

    @DecimalMin(value = "0.01", message = "Price should not be less than 0.01.")
    private BigDecimal price;

}
