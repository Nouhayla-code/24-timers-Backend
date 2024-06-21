package com.example.timers_24_backend.service;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import com.example.timers_24_backend.exception.NotFoundException;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;
import com.example.timers_24_backend.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    private final ResultatRepository resultatRepository;
    private final DeltagerRepository DeltagerRepository;

    private final DisciplinRepository disciplinRepository;

    @Autowired
    public ResultatService(ResultatRepository resultatRepository, DeltagerRepository DeltagerRepository, DisciplinRepository disciplinRepository) {
        this.resultatRepository = resultatRepository;
        this.DeltagerRepository = DeltagerRepository;
        this.disciplinRepository = disciplinRepository;
    }

    public List<ResultatDto> getAllResultater() {
        List<Resultat> resultatList = resultatRepository.findAll();
        return resultatList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResultatDto registrerResultat( ResultatDto resultatDto) {
        Resultat resultat = convertToEntity(resultatDto);
        Resultat savedResultat = resultatRepository.save(resultat);
        return convertToDto(savedResultat);
    }

    private Resultat convertToEntity(ResultatDto resultatDto) {
        Deltager deltager = DeltagerRepository.findById(resultatDto.getDeltagerId())
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + resultatDto.getDeltagerId()));

        Disciplin disciplin = disciplinRepository.findById(resultatDto.getDisciplinId())
                .orElseThrow(() -> new NotFoundException("Disciplin not found with id: " + resultatDto.getDisciplinId()));
         Resultat resultat = new Resultat ();
            resultat.setResultattype(resultatDto.getResultattype());
            resultat.setDato(resultatDto.getDato());
            resultat.setResultatvaerdi(resultatDto.getResultatvaerdi());
            resultat.setDeltager(deltager);
            resultat.setDisciplin(disciplin);
            return resultat;

    }


    public ResultatDto convertToDto(Resultat resultat) {
        UUID deltagerId = resultat.getDeltager() != null ? resultat.getDeltager().getId() : null;
        UUID disciplinId = resultat.getDisciplin() != null ? resultat.getDisciplin().getId() : null;

        return new ResultatDto(
                resultat.getId(),
                resultat.getResultattype(),
                resultat.getDato(),
                resultat.getResultatvaerdi(),
                deltagerId,
                disciplinId
        );
    }
}
