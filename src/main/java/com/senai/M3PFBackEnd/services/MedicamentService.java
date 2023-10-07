package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.entities.MedicamentEntity;
import com.senai.M3PFBackEnd.mappers.MedicamentMapper;
import com.senai.M3PFBackEnd.repositories.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentService {
    @Autowired
    private MedicamentRepository medicamentRepository;

    public MedicamentResponseDto save(MedicamentRequestPostDto newMedicament) {
        MedicamentEntity medicament = MedicamentMapper.map(newMedicament);

        return new MedicamentResponseDto(this.medicamentRepository.save(medicament));
    }
}
