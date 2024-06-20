import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.repository.DeltagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeltagerService {

    private DeltagerRepository deltagerRepository;

    public Deltager opretDeltager(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }

    public Optional<Deltager> findDeltagerById(UUID id) {
        return deltagerRepository.findById(id);
    }

    public List<Deltager> findAllDeltagere() {
        return deltagerRepository.findAll();
    }

    public List<Deltager> findDeltagereByNavn(String navn) {
        return deltagerRepository.findByNavnContaining(navn);
    }

    public Deltager opdaterDeltager(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }

    public void sletDeltager(UUID id) {
        deltagerRepository.deleteById(id);
    }


}
