package fact.it.cyclistservice.service;

import fact.it.cyclistservice.dto.*;
import fact.it.cyclistservice.model.Cyclist;
import fact.it.cyclistservice.model.Gender;
import fact.it.cyclistservice.repository.CyclistRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistService {
    private final CyclistRepository cyclistRepository;

    public CyclistService(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    @PostConstruct
    public void addCyclists() {
        if(cyclistRepository.count() > 0) {
            return;
        }

        cyclistRepository.save(new Cyclist("Yussef", "Dalton", 20, Gender.MALE));
        cyclistRepository.save(new Cyclist("Jan", "Peeters", 30, Gender.MALE));
        cyclistRepository.save(new Cyclist("Els", "Van der Velde", 27, Gender.FEMALE));
        cyclistRepository.save(new Cyclist("Tom", "De Smet", 34, Gender.MALE));
        cyclistRepository.save(new Cyclist("Sofie", "De Clercq", 29, Gender.FEMALE));
        cyclistRepository.save(new Cyclist("Bram", "Van Damme", 40, Gender.MALE));
        cyclistRepository.save(new Cyclist("Lotte", "Claes", 25, Gender.FEMALE));
        cyclistRepository.save(new Cyclist("Karel", "Vermeulen", 37, Gender.MALE));
        cyclistRepository.save(new Cyclist("An", "D'Haese", 33, Gender.FEMALE));
        cyclistRepository.save(new Cyclist("Wim", "De Vos", 41, Gender.MALE));
        cyclistRepository.save(new Cyclist("Inge", "Coene", 28, Gender.FEMALE));
    }

    public List<CyclistResponse> getAllCyclists() {
        List<Cyclist> cyclists = cyclistRepository.findAll();

        return cyclists.stream().map(this::mapToResponse).toList();
    }

    public Optional<CyclistResponse> getCyclist(String id) {
        Optional<Cyclist> cyclist = cyclistRepository.findById(id);

        return cyclist.map(this::mapToResponse);
    }

    private CyclistResponse mapToResponse(Cyclist cyclist) {
        return CyclistResponse.builder()
                .id(cyclist.getId())
                .age(cyclist.getAge())
                .gender(cyclist.getGender().toString())
                .firstName(cyclist.getFirstName())
                .lastName(cyclist.getLastName())
                .build();
    }

    public boolean exists(String id) {
        return cyclistRepository.existsById(id);
    }

    public void deleteById(String id) {
        this.cyclistRepository.deleteById(id);
    }
}
