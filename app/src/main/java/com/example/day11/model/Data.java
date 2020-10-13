package com.example.day11.model;

public class Data {


    private String id, name, address;

    public Data(String id, String name, String address){
        this.id = id;
        this. name = name;
        this.address = address;
    }

    public Data() {

    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
