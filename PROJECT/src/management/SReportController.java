package management;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class SReportController {
    @FXML
    private TableView<SalesReportModel> salesReportTable;

    @FXML
    private TableColumn<SalesReportModel, String> prod_nameRColumn;
    @FXML
    private TableColumn<SalesReportModel, Integer> priceRColumn;
    @FXML
    private TableColumn<SalesReportModel, Integer> qRColumn;
    @FXML
    private JFXButton showsalesRBtn, sortQBtn, totalSalesBtn;
    @FXML
    private TextArea salesRepDisplay;
    @FXML
    private TextField fromDate, toDate;




    @FXML
    private void initialize () {
        prod_nameRColumn.setCellValueFactory(cellData -> cellData.getValue().product_NameProperty());
        priceRColumn.setCellValueFactory(cellData -> cellData.getValue().PriceProperty().asObject());
        qRColumn.setCellValueFactory(cellData -> cellData.getValue().QuantityProperty().asObject());


    }


    @FXML
    private void populateSales (SalesReportModel sales) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<SalesReportModel> salesData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        salesData.add(sales);
        //Set items to the employeeTable
        salesReportTable.setItems(salesData);
    }
    //Set Employee information to Text Area
    @FXML
    private void setSalesInfoToTextArea ( SalesReportModel sales) {
        salesRepDisplay.setText(
               // "Product name: " + sales.getProduct_Name() + "\n"+
                 "Total Price: " + sales.getTotal() + "\n"
                //"Total Quantity : " +sales.getQuantity()

        );
    }

    //Populate Sales for TableView and Display Sales on TextArea
    @FXML
    private void populateAndShowSales(SalesReportModel total) throws ClassNotFoundException {
        if (total != null) {
            populateSales(total);
            setSalesInfoToTextArea(total);
        } else {
            salesRepDisplay.setText("This sale record does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populateSales (ObservableList<SalesReportModel> salesData) throws ClassNotFoundException {
        //Set items to the employeeTable
        salesReportTable.setItems(salesData);
    }
    @FXML
    private void searchSales(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Sales information
            ObservableList<SalesReportModel> salesData = SalesReportDAO.searchSales(fromDate.getText(), toDate.getText());
            //Populate Sales on TableView
            populateSales(salesData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting sales information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void sortQSales(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Sales information
            ObservableList<SalesReportModel> salesData = SalesReportDAO.sortQSales(fromDate.getText(), toDate.getText());
            //Populate Sales on TableView
            populateSales(salesData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting sales information from DB.\n" + e);
            throw e;
        }
    }


    @FXML
private void totalSales(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SalesReportModel total = (SalesReportModel) SalesReportDAO.totalSales(fromDate.getText(), toDate.getText());
        populateAndShowSales(total);

}



}
