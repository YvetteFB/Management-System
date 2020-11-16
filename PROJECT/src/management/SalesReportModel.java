package management;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesReportModel {

    private StringProperty product_name;
    private IntegerProperty quantity;
    private IntegerProperty total;
    private IntegerProperty price;
    private IntegerProperty product_id;
    private StringProperty from_date;
    private StringProperty to_date;


    //constructor
    public SalesReportModel(){
        this.product_name = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.total = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        this.from_date = new SimpleStringProperty();
        this.to_date = new SimpleStringProperty();
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

    public Integer getPrice(){
        return this.price.get();
    }
    public void setPrice(Integer Price){
        this.price.set(Price);
    }
    public IntegerProperty PriceProperty(){
        return price;
    }

    public String getFrom_Date(){
        return from_date.get();
    }
    public void setFrom_date(String from_Date){
        this.from_date.set(from_Date);
    }
    public StringProperty from_DateProperty(){
        return from_date;
    }

    public String getTo_Date(){
        return to_date.get();
    }
    public void setTo_date(String to_Date){
        this.to_date.set(to_Date);
    }
    public StringProperty to_DateProperty(){
        return to_date;
    }

    public Integer getTotal(){
        return total.get();
    }
    public void setTotal(Integer Total){
        this.total.set(Total);
    }
    public IntegerProperty TotalProperty(){
        return total;
    }
}
