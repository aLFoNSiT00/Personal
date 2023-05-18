/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familycontrol;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import familycontrol.agendaBD;
import familycontrol.baseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author Alfonso Santamaria Borondo
 */
public class agenda {

    
   
    
    @FXML
    private Button btAnadirAgenda;

    @FXML
    static AnchorPane formularioAgenda;

    @FXML
    private Button btborrarAgenda;

    @FXML
    private Button btlimpiarAgenda;

    @FXML
    private Circle circulo;

    @FXML
    private TableColumn<agendaBD, String> columApellidos;

    @FXML
    private TableColumn<agendaBD, String> columCumpleaños;

    @FXML
    private TableColumn<agendaBD, String> columDireccion;

    @FXML
    private TableColumn<agendaBD, String> columMail;

    @FXML
    private TableColumn<agendaBD, String> columNombre;

    @FXML
    private TableColumn<agendaBD, String> columTelefono;

    @FXML
    private DatePicker fechaCumpleaños;

    @FXML
    private TableView<agendaBD> tablaAgenda;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    private Connection conexion;
    private PreparedStatement prepare;
    private ResultSet resultado;
    private Statement statement;

    @FXML
    void botonLimpiar(ActionEvent event) {

    }

    public void insertaAgenda() {

        conexion = baseDatos.conexionBD();

        try {

            String insertarDatos = "INSERT INTO agenda (nombre, apellido, telefono, mail, cumpleaños, direccion) "
                    + "VALUES (?,?,?,?,?,?)";
            prepare = conexion.prepareStatement(insertarDatos);
            prepare.setString(1, txtNombre.getText());
            prepare.setString(2, txtApellidos.getText());
            prepare.setString(3, txtTelefono.getText());
            prepare.setString(4, txtMail.getText());
            prepare.setString(5, String.valueOf(fechaCumpleaños.getValue().format(DateTimeFormatter.ISO_DATE)));
            prepare.setString(6, txtDireccion.getText());

            prepare.executeUpdate();

            mostrarListaAgenda();
            //botonLimpiar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<agendaBD> miListaAgenda() {

        ObservableList<agendaBD> listaAgenda = FXCollections.observableArrayList();

        String sentencia = "SELECT * FROM agenda";

        conexion = baseDatos.conexionBD();
        try {

            prepare = conexion.prepareStatement(sentencia);
            resultado = prepare.executeQuery();

            agendaBD agendaBD;
            while (resultado.next()) {
                agendaBD = new agendaBD(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("telefono"),
                        resultado.getString("mail"), resultado.getDate("cumpleaños"), resultado.getString("direccion"));

                listaAgenda.add(agendaBD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAgenda;
    }

    public ObservableList<agendaBD> miListaAgenda;

    public void mostrarListaAgenda() {
        miListaAgenda = miListaAgenda();

        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        columCumpleaños.setCellValueFactory(new PropertyValueFactory<>("cumpleaños"));
        columDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tablaAgenda.setItems(miListaAgenda);
    }
    public int agendaId;

    public void seleccionDatosAgenda() {

        agendaBD agendaBD = (agendaBD) tablaAgenda.getSelectionModel().getSelectedItem();
        int numero = tablaAgenda.getSelectionModel().getSelectedIndex();

        if ((numero - 1) < -1) {
            return;
        }
        agendaId = agendaBD.getId();
        txtNombre.setText(agendaBD.getNombre());
        txtApellidos.setText(agendaBD.getApellido());
        txtTelefono.setText(agendaBD.getTelefono());
        txtMail.setText(agendaBD.getMail());
        fechaCumpleaños.setValue(LocalDate.parse(String.valueOf(agendaBD.getCumpleaños())));
        txtDireccion.setText(agendaBD.getDireccion());

    }
    
    public void borrarAgendaBt(){
        
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtMail.setText("");
        fechaCumpleaños.setValue(null);
        txtDireccion.setText("");
    }
}
