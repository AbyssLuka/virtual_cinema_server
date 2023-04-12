package com.luka.r18.entity.response_object;

public class AnimeTag {

    private String tagName;
    private String tagUuid;
    private String animeViewUuid;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagUuid() {
        return tagUuid;
    }

    public void setTagUuid(String tagUuid) {
        this.tagUuid = tagUuid;
    }

    public String getAnimeViewUuid() {
        return animeViewUuid;
    }

    public void setAnimeViewUuid(String animeViewUuid) {
        this.animeViewUuid = animeViewUuid;
    }
}
