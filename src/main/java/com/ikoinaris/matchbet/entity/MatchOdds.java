package com.ikoinaris.matchbet.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "match_odds")
@Data
@Schema(description = "Represents the odds for a specific match.")
public class MatchOdds {

    @Schema(description = "Unique identifier of the match odd", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Schema(description = "The match associated with this odd")
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    @Schema(description = "Specifier for the odd", example = "1")
    private String specifier;
    @Schema(description = "The odd value", example = "2.5")
    private Double odd;
}
