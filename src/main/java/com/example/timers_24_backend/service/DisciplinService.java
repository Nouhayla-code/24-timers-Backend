import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.repository.DisciplinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DisciplinService {

    private DisciplinRepository disciplinRepository;

    public Disciplin opretDisciplin(Disciplin disciplin) {
        return disciplinRepository.save(disciplin);
    }

    public Optional<Disciplin> findDisciplinById(UUID id) {
        return disciplinRepository.findById(id);
    }

    public List<Disciplin> findAllDiscipliner() {
        return disciplinRepository.findAll();
    }

    public Disciplin opdaterDisciplin(Disciplin disciplin) {
        return disciplinRepository.save(disciplin);
    }

    public void sletDisciplin(UUID id) {
        disciplinRepository.deleteById(id);
    }
}
