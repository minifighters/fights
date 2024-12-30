package com.minifighters.fights.factory;
import com.minifighters.fights.repository.FightRepository;
import com.minifighters.fights.repository.model.FightEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class FightFactoryImpl implements FightFactory {

    private final FightRepository fightRepository;

    @Autowired
    public FightFactoryImpl(FightRepository fightRepository) {

        this.fightRepository = fightRepository;
    }

    @Override
    public FightEntity createFight() {

        FightEntity fightEntity = new FightEntity();
        fightEntity.setFightCode(generateFightCode());
        fightEntity.setPlayers(new ArrayList<>());
        return fightEntity;
    }

    private String generateFightCode() {

        String fightCode;
        do {
            // Fight codes are 4 digits long
            fightCode = RandomStringUtils.randomAlphanumeric(4);
        } while (fightRepository.existsByFightCode(fightCode));
        return fightCode;
    }
}
