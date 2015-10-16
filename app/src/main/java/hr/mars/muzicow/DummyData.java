package hr.mars.muzicow;

/**
 * Created by mars on 14/10/15.
 */
public class DummyData {

    String songName;
    String authorName;
    String youtubeUrl;
    String genre;
    String upVote;
    String downVote;

    // Constructor for dummy data
    public DummyData(String songName, String authorName, String youtubeUrl, String genre, String upVote, String downVote) {
        this.songName = songName;
        this.authorName = authorName;
        this.youtubeUrl = youtubeUrl;
        this.genre = genre;
        this.upVote = upVote;
        this.downVote = downVote;
    }
}