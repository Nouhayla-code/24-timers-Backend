import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import com.example.timers_24_backend.exception.NotFoundException;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;
import com.example.timers_24_backend.repository.ResultatRepository;

@Service
public class ResultatService {

    private final DeltagerRepository deltagerRepository;
    private final DisciplinRepository disciplinRepository;
    private final ResultatRepository resultatRepository;

    public ResultatService(DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository, ResultatRepository resultatRepository) {
        this.deltagerRepository = deltagerRepository;
        this.disciplinRepository = disciplinRepository;
        this.resultatRepository = resultatRepository;
    }

    // Registrer et resultat for en deltager i en disciplin
    public ResultatDto registrerResultat(UUID disciplinId, UUID deltagerId, ResultatDto resultatDto) {
        Disciplin disciplin = findDisciplinById(disciplinId); // Find disciplin baseret på disciplinId
        Deltager deltager = findDeltagerById(deltagerId); // Find deltager baseret på deltagerId

        // Opret en ny Resultat entitet med de angivne detaljer
        Resultat resultat = new Resultat();
        resultat.setPlacering(resultatDto.getPlacering());
        resultat.setResultat(resultatDto.getResultat());
        resultat.setDeltager(deltager); // Sæt deltageren
        resultat.setDisciplin(disciplin); // Sæt disciplinen

        // Gem Resultat entiteten i databasen
        Resultat savedResultat = resultatRepository.save(resultat);

        // Konverter den gemte Resultat entitet til ResultatDto og returnér
        return new ResultatDto(savedResultat.getPlacering(), savedResultat.getResultat());
    }

    // Hjælpefunktion til at finde Disciplin baseret på disciplinId
    private Disciplin findDisciplinById(UUID disciplinId) {
        return disciplinRepository.findById(disciplinId)
                .orElseThrow(() -> new NotFoundException("Disciplin not found with id: " + disciplinId));
    }

    // Hjælpefunktion til at finde Deltager baseret på deltagerId
    private Deltager findDeltagerById(UUID deltagerId) {
        return deltagerRepository.findById(deltagerId)
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + deltagerId));
    }

    // Metoder til at implementere andre krævede funktionaliteter ville komme her
}
