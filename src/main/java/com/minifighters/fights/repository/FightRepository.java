package com.minifighters.fights.repository;
import com.minifighters.fights.repository.model.FightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightRepository extends JpaRepository<FightEntity,String> {


}
