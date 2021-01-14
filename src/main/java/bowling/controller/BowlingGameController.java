package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.IntStream;


public class BowlingGameController {

    public static final int MAX_FRAME_COUNT = 10;

    private static BowlingGames bowlingGames;
    public static void run() {

        int playerNumber = askPlayerNumber();
        String[] playerNames = askPlayerNames(playerNumber);

        Players players = Players.from(playerNames);

        bowlingGames = BowlingGames.from(players);

        printEmptyFrames(bowlingGames);

        IntStream.rangeClosed(1, MAX_FRAME_COUNT)
                .forEach(BowlingGameController::playGame);
    }

    private static void playGame(int frameIndex) {
        bowlingGames.getBowlingGames().stream()
                .forEach(bowlingGame -> playEachFrame(bowlingGame, frameIndex));

    }

    private static void playEachFrame(BowlingGame bowlingGame, int frameIndex) {

        while (bowlingGame.countCompletedFrames() == frameIndex) {

            int knockedDownPins = inputKnockedDownPins(bowlingGame.getPlayer());

            bowlingGame.getFrames().execute(knockedDownPins);

            printCurrentFrames(bowlingGames, MAX_FRAME_COUNT);

            bowlingGame.getFrames().makeNextFrames();
        }
    }

    private static void printCurrentFrames(BowlingGames bowlingGames, int maxFrameCount) {
        ResultView.printCurrentFrames(bowlingGames.getBowlingGames(), maxFrameCount);
    }

    private static void printEmptyFrames(BowlingGames bowlingGames) {
        ResultView.printInitFrames(bowlingGames.getBowlingGames(), MAX_FRAME_COUNT);
    }
    private static void printEmptyFrames(Player player, int maxFrameCount) {
        ResultView.printEmptyFrames(player, maxFrameCount);
    }

    private static String[] askPlayerNames(int playerNumber) {
        return InputView.inputPlayerNames(playerNumber);
    }

    private static int askPlayerNumber() {
        return InputView.inputPlayerNumber();
    }

    private static int inputKnockedDownPins(Player player) {
        return InputView.inputKnockedDownPins(player);
    }





}
