package com.example.reds0n.eventticketqr;

/**
 * Created by reds0n on 3/15/18.
 */

public class category {

      private String Name;
      private String Image;
      private String Price;
      private String Description;



  public category(){

  }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
