package com.minifighters.fights.service;
import com.minifighters.fights.rest.model.MatchmakingSlot;
import org.springframework.stereotype.Service;

@Service
public interface FightService {

    MatchmakingSlot getMatchmakingSlot(String userId);
}
