package com.bknieriem.dice;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    // Die Roll Params
    private final int MIN = 1;
    private final int MAX = 6;
    private final int DIE_COUNT = 2;

    // Die Images
    private final int[] D6 = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };

    //----------------------------------------------------------------------------------------------

    /**
     * On app start, we assign a listener to our single interactive GUI element; the roll button.
     * The roll button simulates two die rolls, then it updates the die images to reflect the rolls.
     * @param savedInstanceState Assures we load any saved instances of our app, if it exists.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------------------------------------------------------------
        // Die Image Locations
        final ImageView DIE_LEFT = findViewById(R.id.img_dieLeft);
        final ImageView DIE_RIGHT = findViewById(R.id.img_dieRight);

        // Die Audio
        final MediaPlayer sfx_roll = MediaPlayer.create(MainActivity.this, R.raw.roll);

        //------------------------------------------------------------------------------------------
        // Roll Button
        Button btn_roll = findViewById(R.id.btn_roll);

        btn_roll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sfx_roll.start();                                 // Roll dice sound effect.
                ArrayList<Integer> result = roll_dice(DIE_COUNT); // Determine rand ints.

//                System.out.println(result.get(0) + " " + result.get(1));

                update_images(result, DIE_LEFT, DIE_RIGHT);       // Assign appropriate images.
            }
        });
        //------------------------------------------------------------------------------------------
    }
    //----------------------------------------------------------------------------------------------

    /**
     * @param DIE_COUNT: Initialized to 2 for the sake of this program, but the method is modular.
     * @return result: An array of x dice rolls.
     */
    private ArrayList<Integer> roll_dice(int DIE_COUNT) {
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < DIE_COUNT; i++) {
            result.add(roll_die(MIN, MAX));
        }
        return result;
    }
    //----------------------------------------------------------------------------------------------

    /**
     * Rolls a single die.
     * @param MIN The predetermined lower bound of the die roll.
     * @param MAX The predetermined upper bound of the die roll.
     * @return Our rolled int value.
     */
    private int roll_die(int MIN, int MAX) {
        Random rnd = new Random();
        return rnd.nextInt((MAX - MIN) + 1) + MIN;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * We use the roll result to find the index of our appropriate die image.
     * @param rolls Our array of integer die roll results.
     */
    private void update_images(ArrayList<Integer> rolls, ImageView DIE_LEFT, ImageView DIE_RIGHT) {
        // Offset (-1) for appropriate index (0 - 5)
        int d1 = rolls.get(0) - 1;
        int d2 = rolls.get(1) - 1;
//        System.out.println(d1 + " " + d2);

        DIE_LEFT.setImageResource(D6[d1]);
        DIE_RIGHT.setImageResource(D6[d2]);
    }
    //----------------------------------------------------------------------------------------------
}