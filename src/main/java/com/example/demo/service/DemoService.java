package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Random;

import com.example.demo.domain.DemoPo;
import com.example.demo.dto.DemoDTO;
import com.example.demo.repository.DemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class DemoService {
    @Autowired
    private DemoRepository demoRepository;

    @PostMapping
    public String insert(DemoDTO demoDTO) {
        DemoPo demoPo = new DemoPo();
        demoPo.setTransactionId(demoDTO.getTransactionId());
        demoPo.setBusinessTime(demoDTO.getBusinessTime());
        demoPo.setPointAmount(demoDTO.getPointAmount());
        Random random = new Random();
        demoPo.setProcesCost(demoDTO.getPointAmount() * (random.nextInt(200) + 300));
        demoPo.setCreatedTime(LocalDateTime.now());
        demoPo.setCreateBy("Ian Huang");
        demoRepository.save(demoPo);
        return "OK";
    }
}
