package com.minifighters.fights.rest;
import com.frameboter.rest.AbstractResource;
import com.minifighters.fights.rest.model.MatchmakingSlot;
import com.minifighters.fights.service.FightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class FightResource extends AbstractResource {

    private final FightService fightService;

    @Autowired
    public FightResource(FightService fightService) {

        this.fightService = fightService;
    }

    // @formatter:off
    @Operation(summary = "Retrieves a matchmaking slot for the user", description = "Retrieves a matchmaking slot or joins the user to a already existing matchmaking slot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matchmaking slot retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content)
    })
    // @formatter:off
    @GetMapping("/matchmakingslot")
    MatchmakingSlot getMatchmakingSlot(@AuthenticationPrincipal Jwt jwt) {
        String userId = getUserId(jwt);
        log.info("FightResource: Started getMatchmakingSlot for user=" + userId);
        MatchmakingSlot matchmakingSlot = fightService.getMatchmakingSlot(userId);
        log.info("FightResource: Finished getMatchmakingSlot for user=" + userId);
        return matchmakingSlot;
    }
}
