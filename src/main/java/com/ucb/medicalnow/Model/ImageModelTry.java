package com.ucb.medicalnow.Model;

import java.sql.Blob;

public class ImageModelTry {

    private Integer resourceId;
    private String name;
    private String type;
    private byte[] pic_byte;

    public ImageModelTry() {
    }

    public ImageModelTry(Integer resourceId, String name, String type, byte[] pic_byte) {
        this.resourceId = resourceId;
        this.name = name;
        this.type = type;
        this.pic_byte = pic_byte;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPic_byte() {
        return pic_byte;
    }

    public void setPic_byte(byte[] pic_byte) {
        this.pic_byte = pic_byte;
    }

    @Override
    public String toString() {
        return "ImageModelTry{" +
                "resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pic_byte=" + pic_byte +
                '}';
    }
}
