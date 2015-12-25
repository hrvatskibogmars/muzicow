package hr.mars.muzicow.Models;

import java.util.Random;

import hr.mars.muzicow.R;

public class DummyDataPlaylist {

    private static final Random RANDOM = new Random();

    public static int getRandomCheeseDrawable() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.drawable.cheese_1;
            case 1:
                return R.drawable.cheese_2;
            case 2:
                return R.drawable.cheese_3;
            case 3:
                return R.drawable.cheese_4;
            case 4:
                return R.drawable.cheese_5;
        }
    }

    public static final String[] sCheeseStrings = {
            "Right Here Right Now","The Passenger" ,"Stuck In The Middle With You","LEK ZA SPAVANJE","Start Wearing Purple",
            "My Companjera","The Gunner's Dream","Wind of change","Žena zna","Buffalo soldier","Dark Side Of The Moon",
            "Riders on the Storm","L.A Woman","The Passenger"
    };
}
