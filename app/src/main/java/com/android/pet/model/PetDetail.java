package com.android.pet.model;

public class PetDetail extends BaseModel {

    /**
     * id : 2
     * name : 金龙飞舞2
     * zip : https://cdn.dreamcapsule.top/1%E6%96%B0%E9%BE%99.zip
     * image : https://cdn.dreamcapsule.top/1559036089841461.jpg
     * useCount : 12200
     * likeCount : 23330
     */

    private int id;
    private String name;
    private String zip;
    private String image;
    private int useCount;
    private int likeCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
