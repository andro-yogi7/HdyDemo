
package com.heady.headydemoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "variant")
public class Variant {

    //@ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;

    //@ColumnInfo(name = "color")
    @SerializedName("color")
    @Expose
    private String color;

    //@ColumnInfo(name = "size")
    @SerializedName("size")
    @Expose
    private Integer size;

    //@ColumnInfo(name = "price")
    @SerializedName("price")
    @Expose
    private Integer price;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Variant() {
    }

    /**
     * 
     * @param id
     * @param price
     * @param color
     * @param size
     */
    public Variant(Integer id, String color, Integer size, Integer price) {
        super();
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return  size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
