package com.senai.M3PFBackEnd.dtos.address;

import com.senai.M3PFBackEnd.entities.AddressEntity;

public record AddressResponseDto(
        Long id,

        String cep,

        String city,

        String state,

        String publicPlace,

        String number,

        String complement,

        String neighborhood,

        String referencePoint
) {
    public AddressResponseDto(AddressEntity address) {
        this(
                address.getId(),
                address.getCep(),
                address.getCity(),
                address.getState(),
                address.getPublicPlace(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getReferencePoint()
        );
    }
}
