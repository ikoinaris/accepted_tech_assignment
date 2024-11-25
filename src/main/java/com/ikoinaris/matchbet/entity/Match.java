package com.ikoinaris.matchbet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ikoinaris.matchbet.model.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "match")
@Data
@Schema(description = "Represents the data of a specific match.")
public class Match {

    @Schema(description = "Unique identifier of the match", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Schema(description = "The two opponents", example = "Roma - Juventus")
    private String description;
    @Schema(description = "The date of the match", example = "2024/03/02")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate matchDate;
    @Schema(description = "The time of the match", example = "15:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime matchTime;
    @Schema(description = "The home team", example = "Roma")
    private String teamA;
    @Schema(description = "The away team", example = "Juventus")
    private String teamB;
    @Schema(description = "Type of sport", example = "Football")
    @Enumerated(EnumType.STRING)
    private Sport sport;
}
