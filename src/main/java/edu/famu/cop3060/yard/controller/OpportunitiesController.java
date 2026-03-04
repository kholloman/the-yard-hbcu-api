package edu.famu.cop3060.yard.controller;

import edu.famu.cop3060.yard.dto.OpportunityDTO;
import edu.famu.cop3060.yard.service.OpportunitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunitiesController {
    private static final Logger log = LoggerFactory.getLogger(OpportunitiesController.class);
    private final OpportunitiesService service;

    public OpportunitiesController(OpportunitiesService service) {
        this.service = service;
    }

    @GetMapping
    public List<OpportunityDTO> getAll(@RequestParam(required = false) String type, @RequestParam(required = false) String q) {
        log.info("GET /api/opportunities — type={}, q={}", type, q);
        return service.list(type, q);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpportunityDTO> getById(@PathVariable String id) {
        log.info("GET /api/opportunities/{}", id);
        return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}