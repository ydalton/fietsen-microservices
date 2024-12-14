package fact.it.cyclistservice.controller;

import fact.it.cyclistservice.dto.CyclistResponse;
import fact.it.cyclistservice.service.CyclistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
