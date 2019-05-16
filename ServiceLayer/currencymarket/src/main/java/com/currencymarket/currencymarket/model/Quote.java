package com.currencymarket.currencymarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote implements Comparable< Quote >{

@SerializedName("time")
@Expose
private int time;
@SerializedName("price")
@Expose
private float price;

public int getTime() {
return time;
}

public void setTime(int time) {
this.time = time;
}

public float getPrice() {
return price;
}

public void setPrice(float price) {
this.price = price;
}

@Override
public String toString() {
    return "price is" + price + "]";
}

public int compareTo(Quote o) {
    return this.getTime()-o.getTime();
}

}
