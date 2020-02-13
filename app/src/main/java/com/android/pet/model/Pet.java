package com.android.pet.model;

public class Pet extends BaseModel {

    /**
     * id : 1
     * name : 恶龙1
     * image : https://cdn.dreamcapsule.top/1560320751496870.jpg
     */

    private int id;
    private String name;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
