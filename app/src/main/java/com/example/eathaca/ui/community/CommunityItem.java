package com.example.eathaca.ui.community;



public class CommunityItem {
    private String farmName;
    private String advertiseTitle;
    private String advertiseContent;
    private int imageUrl;


    public CommunityItem() {
    }


   public CommunityItem(String farmName, String advertiseTitle,String advertiseContent,int imageUrl){
        this.farmName=farmName;
        this.advertiseContent=advertiseContent;
        this.advertiseTitle=advertiseTitle;
        this.imageUrl=imageUrl;
   }

    public int getImageUrl() {
        return imageUrl;
    }

    public String getAdvertiseContent() {
        return advertiseContent;
    }

    public String getAdvertiseTitle() {
        return advertiseTitle;
    }

    public String getFarmName() {
        return farmName;
    }

}