package hr.mars.muzicow;

/**
 * Created by mars on 14/10/15.
 */
public class DummyData {
    String songName;
    String auhtorsName;
    String youtubeUrl;
    String genere;

    public DummyData(String songName, String auhtorsName, String youtubeUrl, String genere) {
        this.songName = songName;
        this.auhtorsName = auhtorsName;
        this.youtubeUrl = youtubeUrl;
        this.genere = genere;
    }
        //test
}
/*
    private void initializeData(){
        dummyDataList = new ArrayList<>();
        dummyDataList.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
        dummyDataList.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
        dummyDataList.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
    }
*/