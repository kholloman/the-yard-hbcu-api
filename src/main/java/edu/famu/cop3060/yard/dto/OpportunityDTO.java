package edu.famu.cop3060.yard.dto;

import java.util.List;

public record OpportunityDTO(
    String id,
    String title,
    String type,
    String sponsor,
    String deadline,
    String description,
    List<String> tags,
    String url
) {}