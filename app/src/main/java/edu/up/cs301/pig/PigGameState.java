package edu.up.cs301.pig;

import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Kavya Mandla
 */
public class PigGameState extends GameState {
    int playerTurn;
    int player0Score;
    int player1Score;
    int currentRunningTotal;
    int currentDieValue;

    PigGameState() {
        super();
    }

    PigGameState(PigGameState p) {
        super();
        playerTurn = p.getPlayerTurn();
        player0Score = p.getPlayer0Score();
        player1Score = p.getPlayer1Score();
        currentRunningTotal = p.getCurrentRunningTotal();
        currentDieValue = p.getCurrentDieValue();

    }

    int getPlayerTurn() {
        return playerTurn;
    }

    int getPlayer0Score() {
        return player0Score;
    }

    int getPlayer1Score() {
        return player1Score;
    }

    int getCurrentRunningTotal() {
        return currentRunningTotal;
    }

    int getCurrentDieValue() {
        return currentDieValue;
    }

    void setPlayerTurn(int curPlayer) {
        playerTurn = 1 - curPlayer;
    }

    void setPlayer0Score(int score) {
        player0Score += score;
    }

    void setPlayer1Score(int score) {
        player1Score += score;
    }

    void setCurrentRunningTotal(int total) {
        if(total == 1) {
            currentRunningTotal = 0;
        }
        else {
            currentRunningTotal = currentRunningTotal + total;
        }
    }

    void setCurrentDieValue(int value) {
        currentDieValue = value;
    }
}
