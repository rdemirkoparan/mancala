package rd.individual.mancala.service;

import rd.individual.mancala.domain.Board;
import rd.individual.mancala.domain.Game;
import rd.individual.mancala.service.rules.RuleApplier;
import rd.individual.mancala.service.rules.impl.GameEndRule;
import rd.individual.mancala.service.rules.impl.LastStoneOnPlayerHouseRule;
import rd.individual.mancala.service.rules.impl.LastStoneOppositeOtherPlayersNonEmptyPitRule;

import java.util.LinkedList;
import java.util.List;

public class GameExecutor {

    private static final List<RuleApplier> rules = new LinkedList<>();

    private GameExecutor() {
        rules.add(new LastStoneOppositeOtherPlayersNonEmptyPitRule());
        rules.add(new LastStoneOnPlayerHouseRule());
        rules.add(new GameEndRule());
    }

    private static class RuleExecutoreInstantinator {
        private static final GameExecutor INSTANCE = new GameExecutor();
    }

    public static GameExecutor getInstance() {
        return RuleExecutoreInstantinator.INSTANCE;
    }

    public void applyRules(Board board) {
        Game game = new Game(board);
        rules.forEach(rule -> rule.applyRule(game));
    }

}
