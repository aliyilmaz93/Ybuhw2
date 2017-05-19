package com.example.ali.ybuhw2;

/**
 * Created by Ali on 14.5.2017.
 */

public class headerlist {
    private String headerText;
    private String headerLink;

    public headerlist(){

    }

    public headerlist(String header,String link){
        headerText = header;
        headerLink = link;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getHeaderLink() {
        return headerLink;
    }

    public void setHeaderLink(String headerLink) {
        this.headerLink = headerLink;
    }
}
