package management;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class sHomeController {

    @FXML
    private JFXButton salesbtn;

    @FXML
    private JFXButton purchasesbtn;

    @FXML
    private JFXButton reportbtn;

    @FXML
    private JFXButton employeesbtn;

    @FXML
    private JFXButton logoutbtn, salesReportBtn, purchReportBtn;

    @FXML
    private AnchorPane supPurchasesPane;

    @FXML
    private AnchorPane supemployeePane, reportsPane;

    @FXML
    private AnchorPane supSalesPane;

    @FXML
    private TextField product_nameTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private Button addSalebtn;

    @FXML
    private BorderPane reportBPane;

    @FXML
    private TextArea Display;

    @FXML
    private TableView<SalesModel> supSalesTable;

    @FXML
    private TableColumn<SalesModel, Integer> salesIDColumn;

    @FXML
    private TableColumn<SalesModel, String> product_nameColumn;

    @FXML
    private TableColumn<SalesModel, Integer> quantityColumn;

    @FXML
    private TableView<PurchasesModel> supPurchTable;

    @FXML
    private TableColumn<PurchasesModel, Integer> IDColumn;
    @FXML
    private TableColumn<PurchasesModel, String> item_nameColumn;
    @FXML
    private TableColumn<PurchasesModel, Integer> purchQuantityColumn;
    @FXML
    private TableColumn<PurchasesModel, Integer> purchPriceColumn;

    @FXML
    private TableView<EmployeeModel> employeeTable;

    @FXML
    private TableColumn<EmployeeModel, Integer> empIDColumn;

    @FXML
    private TableColumn<EmployeeModel, String> empNameColumn;

    @FXML
    private TableColumn<EmployeeModel, Integer> contactColumn;

    @FXML
    private TableColumn<EmployeeModel, String> passwordColumn;

    @FXML
    private TextField salesIDTxt, searchEmpTxt, newContactTxt, idNoTxt, fullNameTxt, contactTxt, passwordTxt, searchTxt, item_nameTxt, purchQTxt,newpurchQTxt, purchPriceTxt;

    @FXML
    private TextField newQTxt;

    @FXML
    private TextArea employeesDisplay, salesDisplay, purchasesDisplay;

    @FXML
    private Button searchBtn, deleteBtn, updateBtn, showallBtn, addempBtn, empSearchBtn, updateEmpBtn, empdeleteBtn, empshowallBtn;

    private String radioButtonLabel = "";


    @FXML
    private void searchSale(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            SalesModel sale = SalesDAO.searchSale(Integer.valueOf(salesIDTxt.getText()));
            //Populate Employee on TableView and Display on TextArea
            populateAndShowSales(sale);
        } catch (SQLException e) {
            e.printStackTrace();
            salesDisplay.setText("Error occurred while getting employee information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void searchSales(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Sales information
            ObservableList<SalesModel> salesData = SalesDAO.searchSales();
            //Populate Sales on TableView
            populateSales(salesData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting sales information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void initialize() {

        //typeColumn.setCellValueFactory(cellData -> cellData.getValue().TypeProperty());
        product_nameColumn.setCellValueFactory(cellData -> cellData.getValue().product_NameProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().QuantityProperty().asObject());
        salesIDColumn.setCellValueFactory(cellData -> cellData.getValue().Sales_idProperty().asObject());
        empIDColumn.setCellValueFactory(cellData -> cellData.getValue().IdProperty().asObject());
        contactColumn.setCellValueFactory(cellData -> cellData.getValue().ContactProperty().asObject());
        empNameColumn.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().PasswordProperty());
        IDColumn.setCellValueFactory(cellData -> cellData.getValue().Purchase_idProperty().asObject());
        item_nameColumn.setCellValueFactory(cellData -> cellData.getValue().item_NameProperty());
        purchQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().pQuantityProperty().asObject());
        purchPriceColumn.setCellValueFactory(cellData -> cellData.getValue().IpriceProperty().asObject());
    }

    @FXML
    private void populateSales(SalesModel sales) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<SalesModel> salesData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        salesData.add(sales);
        //Set items to the employeeTable
        supSalesTable.setItems(salesData);
    }

    //Set Employee information to Text Area
    @FXML
    private void setSalesInfoToTextArea(SalesModel sales) {
        salesDisplay.setText("Sale id: " + sales.getSale_id() + "\n" +
                "Product name: " + sales.getProduct_Name() + "\n" +
                "Quantity: " + sales.getQuantity()
        );
    }

    //Populate Sales for TableView and Display Sales on TextArea
    @FXML
    private void populateAndShowSales(SalesModel sales) throws ClassNotFoundException {
        if (sales != null) {
            populateSales(sales);
            setSalesInfoToTextArea(sales);
        } else {
            salesDisplay.setText("This sale record does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populateSales(ObservableList<SalesModel> salesData) throws ClassNotFoundException {
        //Set items to the employeeTable
        supSalesTable.setItems(salesData);
    }

    //Update sale quantity with the quantity which is written on new quantity field
    @FXML
    private void updateProductQuantity(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SalesDAO.updateProductQuantity(salesIDTxt.getText(), newQTxt.getText());
            salesDisplay.setText("Quantity has been updated for, sale id: " + salesIDTxt.getText() + "\n");
        } catch (SQLException e) {
            salesDisplay.setText("Problem occurred while updating sale: " + e);
        }
    }
    @FXML
    private void Employeesearch(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            EmployeeModel employee = EmployeeDAO.Employeesearch(Integer.valueOf(idNoTxt.getText()));
            //Populate Employee on TableView and Display on TextArea
            populateAndShowEmployees(employee);
        } catch (SQLException e) {
            e.printStackTrace();
            employeesDisplay.setText("Error occurred while getting employee information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Sales information
            ObservableList<EmployeeModel> employeeData = EmployeeDAO.searchEmployees();
            //Populate Sales on TableView
            populateEmployees(employeeData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting sales information from DB.\n" + e);
            throw e;
        }
    }
    //Insert an employee to the DB
    @FXML
    private void insertEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ToggleGroup typeofemployee;
            RadioButton employee, supervisor;
            HBox radios = new HBox();


            typeofemployee = new ToggleGroup();
            employee = new RadioButton("Employee");
            employee.setToggleGroup(typeofemployee);
            if (employee.isSelected()){
                radioButtonLabel = employee.getText();
            }

            supervisor = new RadioButton("Supervisor");
            supervisor.setToggleGroup(typeofemployee);
            radios.getChildren().addAll(employee, supervisor);
            if(supervisor.isSelected()){
                radioButtonLabel = supervisor.getText();
            }

            EmployeeDAO.insertEmployee(idNoTxt.getText(), fullNameTxt.getText(), contactTxt.getText(), passwordTxt.getText(),radioButtonLabel);
            employeesDisplay.setText("Employee inserted! \n");
        } catch (SQLException e) {
            employeesDisplay.setText("Problem occurred while inserting employee " + e);
            throw e;
        }

    }


    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.deleteEmployee(searchEmpTxt.getText());
            employeesDisplay.setText("Employee deleted!  id: " + searchEmpTxt.getText() + "\n");
        } catch (SQLException e) {
            employeesDisplay.setText("Problem occurred while deleting employee " + e);
            throw e;
        }
    }


    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == salesbtn) {
            supSalesPane.toFront();
        } else if (event.getSource() == purchasesbtn) {
            supPurchasesPane.toFront();
        } else if (event.getSource() == reportbtn) {
            reportsPane.toFront();
        } else if (event.getSource() == employeesbtn) {
            supemployeePane.toFront();
        } else if (event.getSource() == logoutbtn) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("base.fxml")));
                stage.setScene(scene);
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }

    }


    @FXML
    private void handleReportButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == salesReportBtn) {
            loadPane("SalesReport");
        } else if (event.getSource() == purchReportBtn) {
            loadPane("PurchasesReport");
        }

    }
    private void loadPane(String pane) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SalesReport.fxml")) ;
        try {
            root = FXMLLoader.load(getClass().getResource(pane +".fxml"));

        }catch (IOException ex){
            System.out.println("the error is " + ex.getMessage());
        }
        reportBPane.setCenter(root);
    }


    @FXML
    private void populateEmployees(EmployeeModel employee) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<EmployeeModel> employeeData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        employeeData.add(employee);
        //Set items to the employeeTable
        employeeTable.setItems(employeeData);
    }
    @FXML
    private void setEmployeeInfoToTextArea(EmployeeModel employee) {
        employeesDisplay.setText("Employee id: " + employee.getId() + "\n" +
                "Name: " + employee.getName() + "\n" +
                "Contact: " + employee.getContact() + "\n" +
                "Password: " + employee.getPassword()
        );
    }

    //Populate Sales for TableView and Display Sales on TextArea
    @FXML
    private void populateAndShowEmployees(EmployeeModel employee) throws ClassNotFoundException {
        if (employee != null) {
            populateEmployees(employee);
            setEmployeeInfoToTextArea(employee);
        } else {
            employeesDisplay.setText("This sale record does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populateEmployees(ObservableList<EmployeeModel> employeeData) throws ClassNotFoundException {
        //Set items to the employeeTable
        employeeTable.setItems(employeeData);
    }

    //Update sale quantity with the quantity which is written on new quantity field
    @FXML
    private void updateEmployeeContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
           EmployeeDAO.updateContact(salesIDTxt.getText(), newQTxt.getText());
            Display.setText("Quantity has been updated for, sale id: " + salesIDTxt.getText() + "\n");
        } catch (SQLException e) {
            Display.setText("Problem occurred while updating sale: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertSale(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SalesDAO.insertSale(product_nameTxt.getText(), quantityTxt.getText());
            salesDisplay.setText("Sale record inserted! \n");
        } catch (SQLException e) {
            salesDisplay.setText("Problem occurred while inserting sale record " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteSale(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SalesDAO.deleteSale(salesIDTxt.getText());
            salesDisplay.setText("Sale deleted! Sale id: " + salesIDTxt.getText() + "\n");
        } catch (SQLException e) {
            salesDisplay.setText("Problem occurred while deleting employee " + e);
            throw e;
        }
    }
    @FXML
    private void searchPurchase (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            PurchasesModel purchase = PurchasesDAO.searchPurchase(searchTxt.getText());
            //Populate Employee on TableView and purchDisplay on TextArea
            populateAndShowPurchases(purchase);
        } catch (SQLException e) {
            e.printStackTrace();
            purchasesDisplay.setText("Error occurred while getting purchase information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void searchPurchases(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Purchases information
            ObservableList<PurchasesModel> purchasesData = PurchasesDAO.searchPurchases();
            //Populate Sales on TableView
            populatePurchases(purchasesData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Purchases information from DB.\n" + e);
            throw e;
        }
    }


    @FXML
    private void populatePurchases (PurchasesModel purchases) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<PurchasesModel> purchasesData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        purchasesData.add(purchases);
        //Set items to the employeeTable
        supPurchTable.setItems(purchasesData);
    }
    //Set Employee information to Text Area
    @FXML
    private void setPurchasesInfoToTextArea ( PurchasesModel purchases) {
        purchasesDisplay.setText("Purchase id: " + purchases.getPurchase_id() + "\n" +
                "Item name: " + purchases.getItem_Name() + "\n"+
                "Quantity: " +purchases.getpQuantity()+ "\n"+
                "Price: " +purchases.getIprice()
        );
    }

    //Populate Sales for TableView and purchDisplay Sales on TextArea
    @FXML
    private void populateAndShowPurchases(PurchasesModel purchases) throws ClassNotFoundException {
        if (purchases != null) {
            populatePurchases(purchases);
            setPurchasesInfoToTextArea(purchases);
        } else {
            purchasesDisplay.setText("This purchase record does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populatePurchases (ObservableList<PurchasesModel> purchasesData) throws ClassNotFoundException {
        //Set items to the employeeTable
        supPurchTable.setItems(purchasesData);
    }

    //Update sale quantity with the quantity which is written on new quantity field
    @FXML
    private void updateItemQuantity (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PurchasesDAO.updateItemQuantity( searchTxt.getText(), newpurchQTxt.getText());
            purchasesDisplay.setText("Quantity has been updated for, purchase id: " + searchTxt.getText() + "\n");
        } catch (SQLException e) {
            purchasesDisplay.setText("Problem occurred while updating purchase: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertPurchase (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PurchasesDAO.insertPurchase(item_nameTxt.getText(),purchQTxt.getText(),purchPriceTxt.getText());
            purchasesDisplay.setText("Purchase Record inserted! \n");
        } catch (SQLException e) {
            purchasesDisplay.setText("Problem occurred while inserting Purchase " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deletePurchase (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PurchasesDAO.deletePurchase(searchTxt.getText());
            purchasesDisplay.setText("Purchase deleted! Purchase id: " + searchTxt.getText() + "\n");
        } catch (SQLException e) {
            purchasesDisplay.setText("Problem occurred while deleting Purchase " + e);
            throw e;
        }
    }


}

