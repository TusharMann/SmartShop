package in.insectiousapp.smartshop;

/**
 * Created by Codev on 12/29/2016.
 */
public class Item {

    int itemId;
    String itemName;
    int itemQty;
    float itemPrice;

    public Item(int itemId, String itemName, int itemQty, float itemPrice)
    {
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemQty=itemQty;
        this.itemPrice=itemPrice;
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
}
