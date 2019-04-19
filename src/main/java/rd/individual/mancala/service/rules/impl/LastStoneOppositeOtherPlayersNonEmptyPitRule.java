package rd.individual.mancala.service.rules.impl;

import rd.individual.mancala.domain.Game;
import rd.individual.mancala.service.rules.RuleApplier;

public class LastStoneOppositeOtherPlayersNonEmptyPitRule implements RuleApplier {

    private static final int OPPONENT_INDEX_DIFF = 12;

    @Override
    public void applyRule(Game game) {
        if (game.getIndex() < 6 && game.getBoard().getPits()[game.getIndex()] == 1) {
            game.getBoard().getPits()[6] += game.getBoard().getPits()[game.getIndex()] + game.getBoard().getPits()[OPPONENT_INDEX_DIFF - game.getIndex()];

            game.getBoard().getPits()[game.getIndex()] = 0;
            game.getBoard().getPits()[OPPONENT_INDEX_DIFF - game.getIndex()] = 0;
        }
    }
}
