package com.example.demo.game;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }
    public Optional<Game> getGame(Long gameId) {
        return gameRepository.findById(gameId);
    }
    public void addNewGame(Game game){
        Optional<Game> gameOptional = gameRepository
                .findGameByFounder(game.getFounder());
        if (gameOptional.isPresent()) {
            throw new IllegalStateException("founder taken");
        }
        gameRepository.save(game);
    }
    public void deleteGame(Long gameId){
        boolean exists = gameRepository.existsById(gameId);
        if (!exists){
            throw new IllegalStateException(
                    "game station with id" +gameId +" does not exists");
        }
        gameRepository.deleteById(gameId);
    }
    @Transactional
    public void updateGame( Long gameId, String stationName, String founder){
        Game game= gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException(
                        "game station with id " +gameId + "does not exists"
                ));
        if (stationName != null &&
                stationName.length() > 0 &&
                !Objects.equals(game.getStationName(), stationName)) {
            game.setStationName(stationName);
        }
        if (founder != null &&
                founder.length() > 0 &&
                !Objects.equals(game.getFounder(), founder)) {
            Optional<Game> gameOptional = gameRepository.findGameByFounder(founder);
            if (gameOptional.isPresent()){
                throw new IllegalStateException("Founder already took");
            }
            game.setFounder(founder);
        }


    }
}
