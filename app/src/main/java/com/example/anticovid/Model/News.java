package com.example.anticovid.Model;

public class News
{
    private String resourceID;
    private String nameNews;
    private String timeNews ;
    private String link;


    public News(String resourceID, String nameNews, String timeNews,String link) {
        this.resourceID = resourceID;
        this.nameNews = nameNews;
        this.timeNews = timeNews;
        this.link= link;
    }

    public String getResourceID() {
        return resourceID;
    }

    public String getNameNews() {
        return nameNews;
    }

    public String getTimeNews() {
        return timeNews;
    }
    public String getLink() {return link; }
    public void setLink(String link){this.link=link;}

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public void setTimeNews(String timeNews) {
        this.timeNews = timeNews;
    }
}
