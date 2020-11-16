package management;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PurchasesModel {

    private StringProperty item_name;
    private IntegerProperty pquantity;
    private IntegerProperty purchase_id;
    private IntegerProperty iprice;

    //constructor
    public PurchasesModel(){
        this.item_name = new SimpleStringProperty();
        this.pquantity = new SimpleIntegerProperty();
        this.purchase_id = new SimpleIntegerProperty();
        this.iprice = new SimpleIntegerProperty();
    }
    //Item name
    public String getItem_Name(){
        return item_name.get();
    }
    public void setItem_name(String item_Name){
        this.item_name.set(item_Name);
    }
    public StringProperty item_NameProperty(){
        return item_name;
    }

    //Quantity
    public Integer getpQuantity(){
        return pquantity.get();
    }
    public void setpQuantity(Integer Quantity){
        this.pquantity.set(Quantity);
    }
    public IntegerProperty pQuantityProperty(){
        return pquantity;
    }



    public Integer getPurchase_id(){
        return this.purchase_id.get();
    }
    public void setPurchase_id(Integer Purchase_id){
        this.purchase_id.set(Purchase_id);
    }
    public IntegerProperty Purchase_idProperty(){
        return purchase_id;
    }


    public Integer getIprice(){
        return this.iprice.get();
    }
    public void setIprice(Integer Iprice){
        this.iprice.set(Iprice);
    }
    public IntegerProperty IpriceProperty(){
        return iprice;
    }






}
