package in.insectiousapp.smartshop;

/**
 * Created by Codev on 12/29/2016.
 */
public class Item {

    int itemId;
    String itemName;
    int itemQuantity;
    float itemPrice;

    public Item(int itemId, String itemName, int itemQuantity, float itemPrice)
    {
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemQuantity=itemQuantity;
        this.itemPrice=itemPrice;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
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

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }



}
