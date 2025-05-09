package br.com.codenoir.domus.application.shared.enums;

import br.com.codenoir.domus.application.exception.NotFoundException;
import lombok.Getter;

@Getter
public enum OwnerType {

    INDIVIDUAL(0),
    LEGAL_ENTITY(1);

    private final int ownerType;

    OwnerType(int ownerType) {
        this.ownerType = ownerType;
    }

    public static OwnerType fromCode(int ownerType) {
        for(OwnerType type : OwnerType.values()) {
            if(type.getOwnerType() == ownerType) {
                return type;
            }
        }

        throw new NotFoundException("Owner type not found");
    }

}
