package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @author Kavya Mandla
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState pigGame;
    GameAction myAction;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        pigGame = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if (pigGame.getPlayerTurn() == playerIdx) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        myAction = action;
        for (int i = 0; i < 2; i ++) {
            if (canMove(i)) {
                /**
                 * Roll the dice. (That is, set the die value to be a random integer in the range 1..6.)
                 * If the die value is anything except 1, add the die value to the current running total.
                 * If the die value is 1, set the current running total to zero, make it the other player’s turn.
                 * Return true, indicating that the move was legal.
                 */
                if (myAction instanceof PigRollAction) {
                    Random random = new Random();
                    int value = random.nextInt(6) + 1;
                    pigGame.setCurrentDieValue(value);
                    if (value == 1) {
                        pigGame.setCurrentRunningTotal(value);
                        pigGame.setPlayerTurn(i);
                    } else {
                        pigGame.setCurrentRunningTotal(value);
                    }
                }
                /**
                 * Add the current running total to the score of the player whose turn it is.
                 * Set the current running total to zero
                 * If there is more than one player in the game, make it the other player’s turn.
                 * Return true, indicating that the move was legal.
                 */
                if (myAction instanceof PigHoldAction) {
                    int totalScore = pigGame.getCurrentRunningTotal();
                    if (i == 0) {
                        pigGame.setPlayer0Score(totalScore);
                    }
                    else {
                        pigGame.setPlayer1Score(totalScore);
                    }
                    pigGame.setCurrentRunningTotal(1);
                    pigGame.setPlayerTurn(i);
                }
                return true;
            }
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState temp = new PigGameState(pigGame);
        p.sendInfo(temp);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if (pigGame.getPlayer0Score() >= 50) {
            return ""+playerNames[0]+" won the game! Score: "+pigGame.getPlayer0Score();
        }
        else if (pigGame.getPlayer1Score() >= 50) {
            return ""+playerNames[1]+" won the game! Score: "+pigGame.getPlayer1Score();
        }
        else {
            return null;
        }
    }

}// class PigLocalGame
