package edu.famu.cop3060.yard.store;

import edu.famu.cop3060.yard.dto.OpportunityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryOpportunityStore {
    private static final Logger log = LoggerFactory.getLogger(InMemoryOpportunityStore.class);
    private final List<OpportunityDTO> opportunities = new ArrayList<>();

    public InMemoryOpportunityStore() {
        seedData();
        log.info("Seeded {} opportunities into the in-memory store.", opportunities.size());
    }

    private void seedData() {
        opportunities.addAll(List.of(
            new OpportunityDTO("opp-001", "UNCF STEM Scholarship", "Scholarship", "UNCF", "2026-04-15", "Funding for STEM majors.", List.of("STEM", "paid"), "https://uncf.org"),
            new OpportunityDTO("opp-002", "Google HBCU Residency", "Fellowship", "Google", "2026-05-01", "Tech career residency.", List.of("tech", "professional"), "https://google.com"),
            new OpportunityDTO("opp-003", "FAMU SGA Leadership", "Organization", "SGA", "2026-09-01", "Student government roles.", List.of("leadership", "campus"), "https://famu.edu"),
            new OpportunityDTO("opp-004", "Homecoming Step Show", "Event", "SGA", "2026-10-20", "Annual campus event.", List.of("culture", "campus"), "https://famu.edu"),
            new OpportunityDTO("opp-005", "Goldman Sachs Internship", "Internship", "Goldman Sachs", "2026-11-15", "Summer analyst program.", List.of("finance", "paid"), "https://gs.com"),
            new OpportunityDTO("opp-006", "Thurgood Marshall Fund", "Scholarship", "TMCF", "2026-06-01", "HBCU student funding.", List.of("undergrad", "funding"), "https://tmcf.org"),
            new OpportunityDTO("opp-007", "Apple Fellow", "Fellowship", "Apple", "2026-03-01", "Engineering fellowship.", List.of("STEM", "tech"), "https://apple.com"),
            new OpportunityDTO("opp-008", "Microsoft Tech Intern", "Internship", "Microsoft", "2026-12-01", "Software intern roles.", List.of("coding", "paid"), "https://microsoft.com")
        ));
    }

    public List<OpportunityDTO> findAll(String type, String q) {
        return opportunities.stream()
            .filter(o -> type == null || o.type().equalsIgnoreCase(type))
            .filter(o -> q == null || o.title().toLowerCase().contains(q.toLowerCase()) || 
                    o.tags().stream().anyMatch(t -> t.equalsIgnoreCase(q)))
            .collect(Collectors.toList());
    }

    public Optional<OpportunityDTO> findById(String id) {
        return opportunities.stream().filter(o -> o.id().equals(id)).findFirst();
    }
}