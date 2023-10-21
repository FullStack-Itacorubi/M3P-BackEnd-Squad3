package com.senai.M3PFBackEnd.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.M3PFBackEnd.dtos.stats.GenericStatDTO;
import com.senai.M3PFBackEnd.repositories.DietRepository;
import com.senai.M3PFBackEnd.repositories.ExamRepository;
import com.senai.M3PFBackEnd.repositories.ExerciseRepository;
import com.senai.M3PFBackEnd.repositories.MedicamentRepository;
import com.senai.M3PFBackEnd.repositories.PatientRepository;
import com.senai.M3PFBackEnd.repositories.QueryRepository;
import com.senai.M3PFBackEnd.repositories.UserRepository;

@Service
public class StatsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private QueryRepository queryRepository;

    public List<GenericStatDTO> getStatistics() {
        List<GenericStatDTO> list = new ArrayList<>();
        list.add(new GenericStatDTO("Usuários", userRepository.count()));
        list.add(new GenericStatDTO("Pacientes", patientRepository.count()));
        list.add(new GenericStatDTO("Medicamentos", medicamentRepository.count()));
        list.add(new GenericStatDTO("Exercícios", exerciseRepository.count()));
        list.add(new GenericStatDTO("Consultas", queryRepository.count()));
        list.add(new GenericStatDTO("Exames", examRepository.count()));
        list.add(new GenericStatDTO("Dietas", dietRepository.count()));

        return list;
    }
}
