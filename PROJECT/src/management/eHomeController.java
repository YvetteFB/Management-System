package management;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class eHomeController {

    @FXML
    private TextArea resultArea;
    @FXML
    private TextField newQuantityText;
    @FXML
    private TextField product_nameText;
    @FXML
    private TextField sales_idText;
    @FXML
    private TextField quantityText;
    @FXML
    private JFXButton sales_btn, purchases_btn, logoutBtn;
    @FXML
    private AnchorPane Sales, Purchases;

    @FXML
    private TextArea purchDisplay;
    @FXML TextField item_nameTxt,qTxt,priceTxt,purchaseIDTxt,newQtxt;


    @FXML
    private TableView sales_table;
    @FXML
    public TableColumn<SalesModel, String> product_nameColumn;
    @FXML
    public TableColumn<SalesModel, Integer> quantityColumn;
    @FXML
    public TableColumn<SalesModel, Integer>  sales_idColumn;


    @FXML
    private TableView purchases_table;
    @FXML
    public TableColumn<PurchasesModel, String> item_nameColumn;
    @FXML
    public TableColumn<PurchasesModel, Integer> pquantityColumn;
    @FXML
    public TableColumn<PurchasesModel, Integer>  purchase_idColumn;
    @FXML
    public TableColumn<PurchasesModel, Integer>  priceColumn;







    @FXML
   private void searchSale (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
       try {
           //Get Employee information
           SalesModel sale = SalesDAO.searchSale(Integer.valueOf(sales_idText.getText()));
           //Populate Employee on TableView and purchDisplay on TextArea
           populateAndShowSales(sale);
       } catch (SQLException e) {
           e.printStackTrace();
           resultArea.setText("Error occurred while getting employee information from DB.\n" + e);
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
        } catch (SQLException e){
            System.out.println("Error occurred while getting sales information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void initialize () {

      //  product_idColumn.setCellValueFactory(cellData -> cellData.getValue().Product_idProperty().asObject());
        product_nameColumn.setCellValueFactory(cellData -> cellData.getValue().product_NameProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().QuantityProperty().asObject());
        sales_idColumn.setCellValueFactory(cellData -> cellData.getValue().Sales_idProperty().asObject());
        item_nameColumn.setCellValueFactory(cellData -> cellData.getValue().item_NameProperty());
        pquantityColumn.setCellValueFactory(cellData -> cellData.getValue().pQuantityProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().IpriceProperty().asObject());
        purchase_idColumn.setCellValueFactory(cellData -> cellData.getValue().Purchase_idProperty().asObject());


    }

    @FXML
    private void populateSales (SalesModel sales) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<SalesModel> salesData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        salesData.add(sales);
        //Set items to the employeeTable
        sales_table.setItems(salesData);
    }
    //Set Employee information to Text Area
    @FXML
    private void setSalesInfoToTextArea ( SalesModel sales) {
        resultArea.setText("Sale id: " + sales.getSale_id() + "\n" +
                "Product name: " + sales.getProduct_Name() + "\n"+
                "Quantity: " +sales.getQuantity()
        );
    }

    //Populate Sales for TableView and purchDisplay Sales on TextArea
    @FXML
    private void populateAndShowSales(SalesModel sales) throws ClassNotFoundException {
        if (sales != null) {
            populateSales(sales);
            setSalesInfoToTextArea(sales);
        } else {
            resultArea.setText("This sale record does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populateSales (ObservableList<SalesModel> salesData) throws ClassNotFoundException {
        //Set items to the employeeTable
        sales_table.setItems(salesData);
    }

    //Update sale quantity with the quantity which is written on new quantity field
    @FXML
    private void updateProductQuantity (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SalesDAO.updateProductQuantity( sales_idText.getText(), newQuantityText.getText());
            resultArea.setText("Quantity has been updated for, sale id: " + sales_idText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating sale: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertSale (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
         SalesDAO.insertSale(product_nameText.getText(),quantityText.getText());
            resultArea.setText("Sale Record inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting sale " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteSale (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SalesDAO.deleteSale(sales_idText.getText());
            resultArea.setText("Sale deleted! Sale id: " + sales_idText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting sale " + e);
            throw e;
        }
    }

    @FXML
    private void searchPurchase (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            PurchasesModel purchase = PurchasesDAO.searchPurchase(purchaseIDTxt.getText());
            //Populate Employee on TableView and purchDisplay on TextArea
            populateAndShowPurchases(purchase);
        } catch (SQLException e) {
            e.printStackTrace();
            purchDisplay.setText("Error occurred while getting purchase information from DB.\n" + e);
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
        purchases_table.setItems(purchasesData);
    }
    //Set Employee information to Text Area
    @FXML
    private void setPurchasesInfoToTextArea ( PurchasesModel purchases) {
        purchDisplay.setText("Purchase id: " + purchases.getPurchase_id() + "\n" +
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
            purchDisplay.setText("This purchase record does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populatePurchases (ObservableList<PurchasesModel> purchasesData) throws ClassNotFoundException {
        //Set items to the employeeTable
        purchases_table.setItems(purchasesData);
    }

    //Update sale quantity with the quantity which is written on new quantity field
    @FXML
    private void updateItemQuantity (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PurchasesDAO.updateItemQuantity( purchaseIDTxt.getText(), newQtxt.getText());
            purchDisplay.setText("Quantity has been updated for, purchase id: " + purchaseIDTxt.getText() + "\n");
        } catch (SQLException e) {
            purchDisplay.setText("Problem occurred while updating purchase: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertPurchase (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PurchasesDAO.insertPurchase(item_nameTxt.getText(),qTxt.getText(),priceTxt.getText());
            purchDisplay.setText("Purchase Record inserted! \n");
        } catch (SQLException e) {
            purchDisplay.setText("Problem occurred while inserting Purchase " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deletePurchase (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PurchasesDAO.deletePurchase(purchaseIDTxt.getText());
            purchDisplay.setText("Purchase deleted! Purchase id: " + purchaseIDTxt.getText() + "\n");
        } catch (SQLException e) {
            purchDisplay.setText("Problem occurred while deleting Purchase " + e);
            throw e;
        }
    }



    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == sales_btn) {
            Sales.toFront();
        } else if (event.getSource() == purchases_btn) {
            Purchases.toFront();
        }else if(event.getSource()== logoutBtn){
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
}