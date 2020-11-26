package com.ucb.medicalnow.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource")
public class ImageModel {

    public ImageModel() {
        super();
    }


    public ImageModel(Integer consultId, String name, String type, byte[] picByte) {
        this.consultId = consultId;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }


    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_id;

    @Column(name = "consult_id")
    private Integer consultId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "tx_id")
    private Integer txId = 0;

    @Column(name = "tx_username")
    private String txUsername = "root";

    @Column(name = "tx_host")
    private String txHost = "127.0.0.1";

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Column(name = "tx_date")
    private Timestamp txDate = timestamp;

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
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

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}