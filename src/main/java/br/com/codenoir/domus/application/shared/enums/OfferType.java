package br.com.codenoir.domus.application.shared.enums;

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

        throw new IllegalArgumentException("Code invalid for OfferType: " + code);
    }

}
