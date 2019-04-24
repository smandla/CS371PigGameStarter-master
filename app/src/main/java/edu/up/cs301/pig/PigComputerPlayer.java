package edu.up.cs301.pig;

import android.os.Handler;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {
    PigLocalGame pigLocalGame = new PigLocalGame();
    int player1 = playerNum;
    PigHoldAction hold;
    PigRollAction roll;

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        if(pigLocalGame.canMove(player1)) {
            if(info instanceof PigGameState){

                Random random = new Random();
                int randNumber = random.nextInt(2);


                roll = new PigRollAction(this);
                hold = new PigHoldAction(this);

                if(randNumber == 0){
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        public void run(){
                            game.sendAction(hold);
                        }
                    }, 2000);

                }
                else{
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){

                        public void run(){
                            game.sendAction(roll);
                        }
                    }, 2000);
                }
            }
        }
        else{
            return;
        }
    }//receiveInfo

}
