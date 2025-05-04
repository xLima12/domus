package br.com.codenoir.domus.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropertyRequestDTO {

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

    @NotNull(message = "Owner id cannot null")
    private String owner_id;

}
