package br.com.codenoir.domus.application.shared.enums;

import br.com.codenoir.domus.application.exception.NotFoundException;

public enum OfferType {

    SALE(0),
    RENT(1);

    private final int offerType;

    OfferType(int offerType) {
        this.offerType = offerType;
    }

    public int getCode() {
        return offerType;
    }

    public static OfferType fromCode(int code) {
        for(OfferType type : OfferType.values()) {
            if(type.getCode() == code) {
                return type;
            }
        }

        throw new NotFoundException("OfferType not found");
    }

}
