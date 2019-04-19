package rd.individual.mancala.service.rules;

import rd.individual.mancala.domain.Game;

public interface RuleApplier {
    void applyRule(Game game);
}
