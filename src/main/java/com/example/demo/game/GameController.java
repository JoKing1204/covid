package com.example.demo.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="api/v1/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService=gameService;
    }

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @GetMapping(path ="{gameId}")
    public Optional<Game> getGame(@PathVariable("gameId") Long id) {
        return gameService.getGame(id);
    }



    @PostMapping
    public void registerNewGame(@RequestBody Game game){
        gameService.addNewGame(game);
    }
    @DeleteMapping(path = "{gameId}")
    public void deleteGame(@PathVariable("gameId") Long id){
        gameService.deleteGame(id);
    }
    @PutMapping(path = "{gameId}")
    public void updateGame(
            @PathVariable("gameId") Long gameId,
            @RequestParam(required = false) String stationName,
            @RequestParam(required = false) String founder){
        gameService.updateGame(gameId, stationName, founder);
    }

}
