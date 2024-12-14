package fact.it.cyclistservice.repository;

import fact.it.cyclistservice.model.Cyclist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CyclistRepository extends MongoRepository<Cyclist, String> {
}
