package fact.it.cyclistservice.controller;

import fact.it.cyclistservice.dto.CyclistResponse;
import fact.it.cyclistservice.service.CyclistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cyclist")
public class CyclistController {
    private CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @GetMapping
    public List<CyclistResponse> findAll() {
        return this.cyclistService.getAllCyclists();
    }

    @GetMapping("{id}")
    public ResponseEntity<CyclistResponse> findById(@PathVariable("id") String id) {
        Optional<CyclistResponse> cyclist = this.cyclistService.getCyclist(id);

        return cyclist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CyclistResponse> delete(@PathVariable("id") String id) {
        if(!this.cyclistService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        this.cyclistService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
