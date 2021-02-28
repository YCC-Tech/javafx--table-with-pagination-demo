package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SampleController implements Initializable {
    
    @FXML
    private Label label; 
          
    @FXML
    private Pagination pagination;
    
    private final TableView<Sample> table = createTable();
    private final List<Sample> data = createData();
    
    private final static int dataSize = 100;
    private final static int rowsPerPage = 10;    
    
    private TableView<Sample> createTable() {

       TableView<Sample> table = new TableView<>();

        TableColumn<Sample, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(param -> param.getValue().id);
        idColumn.setPrefWidth(100);

        TableColumn<Sample, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(param -> param.getValue().foo);
        nameColumn.setPrefWidth(70);       

        table.getColumns().addAll(idColumn, nameColumn);
        return table;       
    }
    
    // this method used to fill data in tableview
    private List<Sample> createData() {
               
      List<Sample> data = new ArrayList<>(dataSize);

        for (int i = 1; i <= dataSize; i++)
            data.add(new Sample(i, "foo " + i, "bar " + i));

        return data;
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {                    
        pagination.setPageFactory(this::createPage);    
    }

    // method to create page inside pagination view
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
        table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return table;
    }
    
      
      // static class for data model
      public static class Sample {

        private final ObservableValue<Integer> id;
        private final SimpleStringProperty foo;
        private final SimpleStringProperty bar;

        private Sample(int id, String foo, String bar) {
            this.id = new SimpleObjectProperty<>(id);
            this.foo = new SimpleStringProperty(foo);
            this.bar = new SimpleStringProperty(bar);
        }
    }
    
}