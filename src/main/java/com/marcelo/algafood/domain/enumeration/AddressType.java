package com.marcelo.algafood.domain.enumeration;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import java.util.stream.Stream;

public enum AddressType {


    RES(1), COM(2), CORR(3), ESC(4);

    private int types;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    AddressType(int types) {
        this.types = types;
    }

    public int getTypes() {
        return types;
    }

    public static AddressType of(int types) {
        return Stream.of(AddressType.values()).filter(a ->
                        a.getTypes() == types).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @PostLoad
    void populateTypesAttrAfterLoad() {
        if (types > 0) {
            this.addressType = AddressType.of(types);
        }
    }

    @PrePersist
    void populateTypesAttrBeforePersist() {
        if (addressType != null) {
            this.types = addressType.getTypes();
        }
    }
}

