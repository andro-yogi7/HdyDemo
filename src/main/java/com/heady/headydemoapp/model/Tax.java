
package com.heady.headydemoapp.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "tax")
public class Tax {

    //@ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    //@ColumnInfo(name = "value")
    @SerializedName("value")
    @Expose
    private Double value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tax() {
    }

    /**
     * 
     * @param name
     * @param value
     */
    public Tax(String name, Double value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
