# Mancala Board Game
This implementation is a 2-player turn-based game of well know Mancala board game, which users can enjoy via shared screen.

Board Setup
```
Both of the players have 6 small pit with 6 stones inside them and one empty big pit. 
```

## Rules
### Game Play

The direction of the game is towards the right. The first player starts with any of his pits and sows the stones to the right, including his big pit but not the opponent's one. If and only if the last stone placed in the player's big pit, the player gets another turn.

### Capturing Stones

During the game, the last stone, which sows during the moves, may placed in an own empty pit. In this case, the player captures all stones in the opponent's pit, which just the opposite of the current pit, and all stones in the player's pit.

### The Game Ends

Whenever any player consumes all of the stones in the player's own pits, the game is over. The other player collects all the stones in the player's own pits and places them to the player's big pit. The winner is determined according to stone count in the big pits, the highest number of stone brings the gold medal to the owner.

## Usage

### Execution

```
Clone to your local repository: git clone https://github.com/rdemirkoparan/mancala.git
Change directory: cd mancala/
Compile: mvn compile
Run: mvn spring-boot:run
Open you favorite browser and go: http://127.0.0.1:8080/index.html
```
 
### Running the tests

There is one test class exists. This test is acts as functional test rather than unit test.

```
mvn test
```

### Further reading

Take a look at the doc directory

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

