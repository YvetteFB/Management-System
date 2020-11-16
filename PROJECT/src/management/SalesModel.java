package management;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;


public class SalesModel {

    private StringProperty product_name;
    private IntegerProperty quantity;
    private IntegerProperty sale_id;
    private Date from_Date, to_date;


//constructor
    public SalesModel(){
        this.product_name = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.sale_id = new SimpleIntegerProperty();


    }
//Product name
    public String getProduct_Name(){
        return product_name.get();
    }
    public void setProduct_name(String product_Name){
        this.product_name.set(product_Name);
    }
    public StringProperty product_NameProperty(){
        return product_name;
    }

//Quantity
    public Integer getQuantity(){
        return quantity.get();
    }
    public void setQuantity(Integer Quantity){
        this.quantity.set(Quantity);
    }
    public IntegerProperty QuantityProperty(){
        return quantity;
    }



    public Integer getSale_id(){
        return this.sale_id.get();
    }
    public void setSale_id(Integer Sale_id){
        this.sale_id.set(Sale_id);
    }
    public IntegerProperty Sales_idProperty(){
        return sale_id;
    }

}
