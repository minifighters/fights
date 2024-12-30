package com.minifighters.fights.repository.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class FightEntity {

    @Id
    private String fightCode;

    @ElementCollection
    @Column(name = "player")
    @CollectionTable(name = "FightEntity_players", joinColumns = @JoinColumn(name = "fightCode"))
    private List<String> players = new ArrayList<>();

}
