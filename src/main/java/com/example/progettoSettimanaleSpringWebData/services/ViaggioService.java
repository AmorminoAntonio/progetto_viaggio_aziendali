package com.example.progettoSettimanaleSpringWebData.services;

import com.example.progettoSettimanaleSpringWebData.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    ViaggioRepository viaggioRepository;
}
