package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.address.AddressRequestPostDto;
import com.senai.M3PFBackEnd.entities.AddressEntity;

public class AddressMapper {
    private AddressMapper() {}

    public static AddressEntity map(AddressRequestPostDto source) {
        AddressEntity target = new AddressEntity();

        target.setCep(source.cep());
        target.setCity(source.city());
        target.setState(source.state());
        target.setPublicPlace(source.publicPlace());
        target.setNumber(source.number());
        target.setComplement(source.complement());
        target.setNeighborhood(source.neighborhood());
        target.setReferencePoint(source.referencePoint());

        return target;
    }
}
