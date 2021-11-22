package com.marcelo.algafood.domain.enumeration;


public enum TelephoneType {

    RES("Residential"), CEL("Cellular"), COM("Commercial"), REC("Read");

    private String description;

    TelephoneType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

//    public static TelephoneTypes of (String types) {
//        return Stream.of(TelephoneTypes.values()).filter(t -> t.getDescription()
//                .equals(types)).findFirst().orElseThrow(IllegalArgumentException::new);
//    }
