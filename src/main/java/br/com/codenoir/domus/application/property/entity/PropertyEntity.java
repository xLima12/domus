package br.com.codenoir.domus.application.property.entity;

import br.com.codenoir.domus.application.owner.entity.OwnerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_property")
@Data
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Name cannot null")
    private String name;

    @NotNull(message = "Address cannot null")
    private String address;

    @Min(value = 0, message = "The number to bedrooms should not be less than 0 (zero).")
    @Max(value = 50, message = "The number to bedrooms should not be more than 50 (fifty).")
    private int bedrooms;

    @Min(value = 0, message = "The number to living room should not be less than 0 (zero).")
    @Max(value = 50, message = "The number to living room should not be more than 50 (fifty).")
    private int livingRoom;

    @Min(value = 0, message = "The number to kitchen should not be less than 0 (zero).")
    @Max(value = 50, message = "The number to kitchen should not be more than 50 (fifty).")
    private int kitchen;

    @Min(value = 0, message = "The number to bathroom should not be less than 0 (zero).")
    @Max(value = 50, message = "The number to bathroom should not be more than 50 (fifty).")
    private int bathroom;

    @Min(value = 0, message = "The number to garage should not be less than 0 (zero).")
    @Max(value = 50, message = "The number to garage should not be more than 50 (fifty).")
    private int garage;

    @Min(value = 0, message = "The number to basement should not be less than 0 (zero).")
    @Max(value = 50, message = "The number to basement should not be more than 50 (fifty).")
    private int basement;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable=false, updatable=false)
    private OwnerEntity owner;

}
