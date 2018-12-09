
package com.heady.headydemoapp.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*@Entity(tableName = "product")*/
public class Product {

    //@ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;

    //@ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    //@ColumnInfo(name = "date_added")
    @SerializedName("date_added")
    @Expose
    private String dateAdded;

    //@ColumnInfo(name = "variants")
    @SerializedName("variants")
    @Expose
    private List<Variant> variants = null;

    //@ColumnInfo(name = "tax")
    @SerializedName("tax")
    @Expose
    private Tax tax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Product() {
    }

    /**
     * 
     * @param id
     * @param tax
     * @param name
     * @param variants
     * @param dateAdded
     */
    public Product(Integer id, String name, String dateAdded, List<Variant> variants, Tax tax) {
        super();
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.variants = variants;
        this.tax = tax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

}
