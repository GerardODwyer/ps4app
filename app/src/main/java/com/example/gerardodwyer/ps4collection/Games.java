package com.example.gerardodwyer.ps4collection;
public class Games {

    private String id;
    private String GameTitle;
    private String ReleaseDate;
    private String thumb;

    public Games(String id, String gameTitle, String releaseDate, String thumb) {
        this.id = id;
        GameTitle = gameTitle;
        ReleaseDate = releaseDate;
        this.thumb = thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameTitle() {
        return GameTitle;
    }

    public void setGameTitle(String gameTitle) {
        GameTitle = gameTitle;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}


