package com.example.timers_24_backend.api;

import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.service.DisciplinService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/disciplins")
public class DisciplinController {

    @Autowired
    private DisciplinService disciplinService;

    @PostConstruct

    public void init() {
        List<Disciplin> disciplins = new ArrayList<>();

        disciplins.add(new Disciplin("1-milløb (atletik)", "time"));
        disciplins.add(new Disciplin("10.000-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("100-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("110 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("1500-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("200 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("200-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("3000-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("4 × 100-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("4 × 400-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("400 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("400-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("4x400-meterløb blandet hold (atletik)", "time"));
        disciplins.add(new Disciplin("5000-meter-løb (atletik)", "time"));
        disciplins.add(new Disciplin("60 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("60-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("800-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("Cross (løbesport)", "time"));
        disciplins.add(new Disciplin("Diskoskast (atletik)", "distance"));
        disciplins.add(new Disciplin("Femkamp (atletik)", "points"));
        disciplins.add(new Disciplin("Forhindringsløb (atletik)", "time"));
        disciplins.add(new Disciplin("Halvmaratonløb (løbesport)", "time"));
        disciplins.add(new Disciplin("Hammerkast (atletik)", "distance"));
        disciplins.add(new Disciplin("Højdespring (atletik)", "height"));
        disciplins.add(new Disciplin("Højdespring uden tilløb (atletik)", "height"));

        disciplinService.createDisciplins(disciplins);
    }
}
