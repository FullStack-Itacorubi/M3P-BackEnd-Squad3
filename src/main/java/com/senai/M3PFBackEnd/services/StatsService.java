package com.senai.M3PFBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserRepository userRepository;

    @Autowired
    DietRepository dietRepository;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    QueryRepository queryRepository;

    public void getStatistics() {

    }
}
