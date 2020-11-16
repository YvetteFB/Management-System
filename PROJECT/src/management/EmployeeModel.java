package management;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeModel {
    private StringProperty name;
    private IntegerProperty id;
    private IntegerProperty contact;
    private StringProperty password;
    private StringProperty type;


    public EmployeeModel(){
        this.name = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
        this.contact = new SimpleIntegerProperty();
        this.password = new SimpleStringProperty();
        this.type = new SimpleStringProperty();

    }
    public String getName(){
        return name.get();
    }
    public void setName(String Name){
        this.name.set(Name);
    }
    public StringProperty NameProperty(){
        return name;
    }

    public Integer getId(){
        return id.get();
    }
    public void setId(Integer Id){
        this.id.set(Id);
    }
    public IntegerProperty IdProperty(){
        return id;
    }

    public Integer getContact(){
        return contact.get();
    }
    public void setContact(Integer Contact){
        this.contact.set(Contact);
    }
    public IntegerProperty ContactProperty(){
        return contact;
    }

    public String getPassword(){
        return password.get();
    }
    public void setPassword(String Password){
        this.password.set(Password);
    }
    public StringProperty PasswordProperty(){
        return password;
    }

    public String getType(){
        return type.get();
    }
    public void setType(String Type){
        this.type.set(Type);
    }
    public StringProperty TypeProperty(){
        return type;
    }
}
