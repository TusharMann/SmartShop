package in.insectiousapp.smartshop;

import java.io.Serializable;

/**
 * Created by Codev on 12/29/2016.
 */
public class Item implements Serializable{

    int itemId;
    String itemName;
    int itemQty;
    float itemPrice;
    int itemCheck;

    public Item(int itemId, String itemName, int itemQty, float itemPrice, int itemCheck)
    {
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemQty=itemQty;
        this.itemPrice=itemPrice;
        this.itemCheck=itemCheck;
    }

    public int getItemQuantity() {
        return itemQty;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemCheck() {
        return itemCheck;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public void setItemCheck(int itemCheck) {
        this.itemCheck = itemCheck;
    }
}
