package com.heady.headydemoapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/*@Entity(tableName = "category")*/
public class Category {
    //@ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;

    //@ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    //@ColumnInfo(name = "products")
    @SerializedName("products")
    @Expose

    private List<Product> products = null;

  /*  @ColumnInfo(name = "child_categories")
    @SerializedName("child_categories")
    @Expose
    private List<Integer> childCategories = null;*/

    /**
     * No args constructor for use in serialization
     * 
     */
    //@Ignore
    public Category() {
    }

    /**
     * 
     * @param childCategories
     * @param id
     * @param name
     * @param products
     */
    public Category(Integer id, String name, List<Product> products, List<Integer> childCategories) {
        super();
        this.id = id;
        this.name = name;
        this.products = products;
        //this.childCategories = childCategories;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

   /* public List<Integer> getChildCategories() {
        return childCategories;
    }
*/
  /*  public void setChildCategories(List<Integer> childCategories) {
        this.childCategories = childCategories;
    }
*/
}
