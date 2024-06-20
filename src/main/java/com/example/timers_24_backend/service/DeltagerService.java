package com.example.timers_24_backend.service;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.exception.NotFoundException;
import com.example.timers_24_backend.repository.DeltagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeltagerService {

    private final DeltagerRepository deltagerRepository;

    @Autowired
    public DeltagerService(DeltagerRepository deltagerRepository) {
        this.deltagerRepository = deltagerRepository;
    }


    public DeltagerDto createDeltager(DeltagerDto deltagerDto) {
        Deltager deltager = convertToEntity(deltagerDto);
        Deltager savedDeltager = deltagerRepository.save(deltager);
        return convertToDto(savedDeltager);
    }

    public DeltagerDto getDeltagerById(UUID id) {
        Optional<Deltager> optionalDeltager = deltagerRepository.findById(id);
        return optionalDeltager.map(this::convertToDto).orElse(null);
    }

    public List<DeltagerDto> getAllDeltager() {
        List<Deltager> deltagere = deltagerRepository.findAll();
        return deltagere.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DeltagerDto updateDeltager(UUID id, DeltagerDto deltagerDto) {
        Optional<Deltager> optionalDeltager = deltagerRepository.findById(id);
        if (optionalDeltager.isPresent()) {
            Deltager existingDeltager = optionalDeltager.get();
            existingDeltager.setNavn(deltagerDto.getNavn());
            existingDeltager.setAlder(deltagerDto.getAlder());
            existingDeltager.setKon(deltagerDto.getKon());
            existingDeltager.setKlub(deltagerDto.getKlub());
            existingDeltager.setResultater(deltagerDto.getResultater());
            existingDeltager.setDiscipliner(deltagerDto.getDiscipliner());
            Deltager updatedDeltager = deltagerRepository.save(existingDeltager);
            return convertToDto(updatedDeltager);
        } else {
            throw new NotFoundException("Deltager not found with id: " + id);
        }
    }

    public boolean deleteDeltager(UUID id) {
        if (deltagerRepository.existsById(id)) {
            deltagerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Deltager convertToEntity(DeltagerDto deltagerDto) {
        return new Deltager(
                deltagerDto.getId(),
                deltagerDto.getNavn(),
                deltagerDto.getKon(),
                deltagerDto.getAlder(),
                deltagerDto.getKlub(),
                deltagerDto.getResultater(),
                deltagerDto.getDiscipliner()
        );
    }

    private DeltagerDto convertToDto(Deltager deltager) {
        return new DeltagerDto(
                deltager.getId(),
                deltager.getNavn(),
                deltager.getKon(),
                deltager.getAlder(),
                deltager.getKlub()
                );
    }
}
