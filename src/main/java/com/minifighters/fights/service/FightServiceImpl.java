package com.minifighters.fights.service;
import com.minifighters.fights.factory.FightFactory;
import com.minifighters.fights.repository.FightRepository;
import com.minifighters.fights.repository.model.FightEntity;
import com.minifighters.fights.rest.model.MatchmakingSlot;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepository;
    private final FightFactory fightFactory;
    private final ModelMapper modelMapper;

    @Autowired
    public FightServiceImpl(FightRepository fightRepository, FightFactory fightFactory, ModelMapper modelMapper) {

        this.fightRepository = fightRepository;
        this.fightFactory = fightFactory;
        this.modelMapper = modelMapper;
    }

    @Override
    public MatchmakingSlot getMatchmakingSlot(String userId) {

        Optional<FightEntity> fightOptional = fightRepository.findFightWithPlayersCountLessThan(2L);
        if (fightOptional.isPresent()) {
            return convertToMatchmakingSlot(fightOptional.get());
        }

        FightEntity fight = fightFactory.createFight();
        fight.getPlayers().add(userId);
        FightEntity matchmakingSlot = fightRepository.save(fight);
        return convertToMatchmakingSlot(matchmakingSlot);
    }

    private MatchmakingSlot convertToMatchmakingSlot(FightEntity fightEntity) {

        MatchmakingSlot matchmakingSlot = modelMapper.map(fightEntity, MatchmakingSlot.class);
        matchmakingSlot.setFightCode(fightEntity.getFightCode());
        return matchmakingSlot;
    }
}
