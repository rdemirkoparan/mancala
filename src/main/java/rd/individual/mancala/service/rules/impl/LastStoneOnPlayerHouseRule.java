package rd.individual.mancala.service.rules.impl;

import rd.individual.mancala.domain.Game;
import rd.individual.mancala.service.rules.RuleApplier;

public class LastStoneOnPlayerHouseRule implements RuleApplier {

    @Override
    public void applyRule(Game game) {
        if (game.getIndex() == 6) {
            game.getBoard().setNextPlayer(game.getBoard().getPlayer());
        } else {
            if (game.getBoard().getPlayer().equals("1")) {
                game.getBoard().setNextPlayer("2");
            } else {
                game.getBoard().setNextPlayer("1");
            }
        }
    }
}
