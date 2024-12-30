package com.minifighters.fights.repository;
import com.minifighters.fights.repository.model.FightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FightRepository extends JpaRepository<FightEntity, String> {

    @Query("select f from FightEntity f where size(f.players) < :playerCount")
    Optional<FightEntity> findFightWithPlayersCountLessThan(@Param("playerCount") Long playerCount);

    boolean existsByFightCode(String fightCode);
}
