import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import com.example.timers_24_backend.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;

    public Resultat opretResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public Optional<Resultat> findResultatById(UUID id) {
        return resultatRepository.findById(id);
    }

    public List<Resultat> findAllResultater() {
        return resultatRepository.findAll();
    }

    public List<Resultat> findResultaterByDisciplin(Disciplin disciplin) {
        return resultatRepository.findByDisciplin(disciplin);
    }

    public Resultat opdaterResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public void sletResultat(UUID id) {
        resultatRepository.deleteById(id);
    }

    public List<Resultat> findResultaterByDisciplinAndSort(Disciplin disciplin) {
        return resultatRepository.findByDisciplinOrderByResultatvaerdi(disciplin);
    }

    public List<Resultat> findResultaterByDisciplinAndGenderAndAgeGroup(Disciplin disciplin, String gender, String ageGroup) {
        return resultatRepository.findByDisciplinAndDeltagerKonAndDeltagerAlderBetween(disciplin, gender, getAgeGroupMin(ageGroup), getAgeGroupMax(ageGroup));
    }

    private int getAgeGroupMin(String ageGroup) {
        switch (ageGroup) {
            case "Børn": return 6;
            case "Unge": return 10;
            case "Junior": return 14;
            case "Voksne": return 23;
            case "Senior": return 41;
            default: return 0;
        }
    }

    private int getAgeGroupMax(String ageGroup) {
        switch (ageGroup) {
            case "Børn": return 9;
            case "Unge": return 13;
            case "Junior": return 22;
            case "Voksne": return 40;
            case "Senior": return Integer.MAX_VALUE;
            default: return Integer.MAX_VALUE;
        }
    }
}
