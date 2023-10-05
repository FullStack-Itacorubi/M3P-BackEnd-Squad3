package com.senai.M3PFBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.M3PFBackEnd.entities.LogMessage;
import com.senai.M3PFBackEnd.repositories.LogsRepository;

@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;

    public void saveLog(String message) {
        this.logsRepository.save(new LogMessage(message));
    }
}
