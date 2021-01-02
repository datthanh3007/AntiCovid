package com.example.anticovid.Model;

public class Guide {
    private int resource;
    private String nameTitle;
    private String childGuide;
    private boolean expandable;

    public Guide(int resource, String nameTitle, String childGuide) {
        this.resource = resource;
        this.nameTitle = nameTitle;
        this.childGuide = childGuide;
        this.expandable= false;

    }


    public void setResource(int resource) {
        this.resource = resource;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }



    public void setChildGuide(String childGuide) {
        this.childGuide = childGuide;
    }

    public String getChildGuide() {
        return childGuide;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public int getResource() {
        return resource;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "resource=" + resource +
                ", nameTitle='" + nameTitle + '\'' +
                ", childGuide='" + childGuide + '\'' +
                ", expandable=" + expandable +
                '}';
    }
}
