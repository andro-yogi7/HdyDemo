
package com.heady.headydemoapp.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product_ {

    //@ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;

   // @ColumnInfo(name = "view_count")
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;

    //@ColumnInfo(name = "order_count")
    @SerializedName("order_count")
    @Expose
    private Integer orderCount;

   // @ColumnInfo(name = "shares")
    @SerializedName("shares")
    @Expose
    private Integer shares;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Product_() {
    }

    /**
     * 
     * @param shares
     * @param id
     * @param orderCount
     * @param viewCount
     */
    public Product_(Integer id, Integer viewCount, Integer orderCount, Integer shares) {
        super();
        this.id = id;
        this.viewCount = viewCount;
        this.orderCount = orderCount;
        this.shares = shares;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

}
