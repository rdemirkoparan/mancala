package rd.individual.mancala.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rd.individual.mancala.domain.Board;
import rd.individual.mancala.service.GameExecutor;

import java.io.IOException;

@RestController
public class GameController {

    @RequestMapping(value = "/move",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Board move(@RequestBody Board board) throws IOException {
        GameExecutor.getInstance().applyRules(board);
        return board;
    }

}