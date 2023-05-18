/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familycontrol;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Data
 */
public class CuentasController implements Initializable {

    @FXML
    private Button btIngreso;

    @FXML
    private Button btLimpiaIngreso;

    @FXML
    private Button btIngresoVariable;

    @FXML
    private TableColumn<cuentaFijoBD, String> columConceptoFijo;

    @FXML
    private TableColumn<cuentaVariableBD, String> columConceptoVariable;

    @FXML
    private TableColumn<cuentaFijoBD, String> columFechaFijo;

    @FXML
    private TableColumn<cuentaVariableBD, String> columFechaVariable;

    @FXML
    private TableColumn<cuentaFijoBD, String> columImporteFijo;

    @FXML
    private TableColumn<cuentaVariableBD, String> columImporteVariable;

    @FXML
    private DatePicker fechaFijo;

    @FXML
    private DatePicker fechaVariable;

    @FXML
    private AnchorPane formularioCuentas;

    @FXML
    private Label labelFijos;

    @FXML
    private Label labelVariables;

    @FXML
    private TableView<cuentaVariableBD> tablaVariable;

    @FXML
    private TableView<cuentaFijoBD> tablaFijo;

    @FXML
    private Label total;

    @FXML
    private TextField txtAdicional;

    @FXML
    private TextField txtConcepto;

    @FXML
    private TextField txtConceptoFijo;

    @FXML
    private TextField txtFijos;

    @FXML
    private TextField txtInicial;

    @FXML
    private TextField txtVariables;

    private Connection conexion;
    private PreparedStatement prepare;
    private ResultSet resultado;
    private Statement statement;

    public void insertarDatoFijo() {

        conexion = baseDatos.conexionBD();

        try {
            String insertarDatos = "INSERT into cuentafijo(gastoFijo, conceptoFijo, fechaFijo) "
                    + "VALUES(?,?,?)";
            prepare = conexion.prepareStatement(insertarDatos);

            prepare.setString(1, String.valueOf(txtFijos.getText()));
            prepare.setString(2, txtConceptoFijo.getText());
            prepare.setString(3, String.valueOf(fechaFijo.getValue().format(DateTimeFormatter.ISO_DATE)));

            prepare.executeUpdate();

            mostrarListaCuentasFijo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarDatoVariable() {

        conexion = baseDatos.conexionBD();

        try {
            String insertarDatos = "INSERT into cuentavariable(gastoVariable, conceptoVariable, fechaVariable) "
                    + "VALUES(?,?,?)";
            prepare = conexion.prepareStatement(insertarDatos);

            prepare.setString(1, String.valueOf(txtVariables.getText()));
            prepare.setString(2, txtConcepto.getText());
            prepare.setString(3, String.valueOf(fechaVariable.getValue().format(DateTimeFormatter.ISO_DATE)));

            prepare.executeUpdate();

            mostrarListaCuentasFijo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*    public void insertarDato() {

        conexion = baseDatos.conexionBD();

        try {
            String insertarDatos = "INSERT into cuentas(saldoInicial, ingresoAdicional, gastoFijo, conceptoFijo, gastoVariable, concepto) "
                    + "VALUES(?,?,?,?,?,?)";
            prepare = conexion.prepareStatement(insertarDatos);

            prepare.setString(1, String.valueOf(txtInicial.getText()));
            prepare.setString(2, String.valueOf(txtAdicional.getText()));
            prepare.setString(3, String.valueOf(txtFijos.getText()));
            prepare.setString(4, txtConceptoFijo.getText());
            prepare.setString(5, String.valueOf(txtVariables.getText()));
            prepare.setString(6, String.valueOf(txtConcepto.getText()));

            prepare.executeUpdate();
            
            mostrarListaCuentas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public ObservableList<cuentaFijoBD> miListaCuentaFijo() {

        ObservableList<cuentaFijoBD> listaCuentaFijo = FXCollections.observableArrayList();

        String sentencia = "SELECT *FROM cuentafijo";
        conexion = baseDatos.conexionBD();

        try {

            prepare = conexion.prepareStatement(sentencia);
            resultado = prepare.executeQuery();

            cuentaFijoBD cuentaFijoBD = null;
            while (resultado.next()) {
                cuentaFijoBD = new cuentaFijoBD(resultado.getInt("id"), resultado.getDouble("gastoFijo"), resultado.getString("conceptoFijo"),
                        resultado.getDate("fechaFijo"));

            }

            listaCuentaFijo.add(cuentaFijoBD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaCuentaFijo;
    }

    public ObservableList<cuentaVariableBD> miListaCuentaVariable() {

        ObservableList<cuentaVariableBD> listaCuentaVariable = FXCollections.observableArrayList();

        String sentencia = "SELECT *FROM cuentavariable";
        conexion = baseDatos.conexionBD();

        try {

            prepare = conexion.prepareStatement(sentencia);
            resultado = prepare.executeQuery();

            cuentaVariableBD cuentaVariableBD = null;
            while (resultado.next()) {
                cuentaVariableBD = new cuentaVariableBD(resultado.getInt("id"), resultado.getDouble("gastoVariable"), resultado.getString("conceptoVariable"),
                        resultado.getDate("fechaVariable"));

            }

            listaCuentaVariable.add(cuentaVariableBD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaCuentaVariable;
    }

    /*public ObservableList<cuentasBD> miListaCuentas() {

        ObservableList<cuentasBD> listaCuentas = FXCollections.observableArrayList();

        String sentencia = "SELECT id, fechaGastoFijo, fechaGastoVariable FROM cuentas";
        conexion = baseDatos.conexionBD();

        try {

            prepare = conexion.prepareStatement(sentencia);
            resultado = prepare.executeQuery();

            cuentasBD cuentasBD = null;
            while (resultado.next()) {
                cuentasBD = new cuentasBD(resultado.getInt("id"), resultado.getDouble("saldoInicial"), resultado.getDouble("ingresoAdicional"),
                        resultado.getDouble("gastoFijo"), resultado.getString("conceptoFijo"), resultado.getDouble("gastoVariable"), resultado.getString("concepto"));
            }

            listaCuentas.add(cuentasBD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaCuentas;
    }*/
    public ObservableList<cuentaVariableBD> miListaCuentaVariable;

    public void mostrarListaCuentasVariable() {
        miListaCuentaVariable = miListaCuentaVariable();

        columFechaVariable.setCellValueFactory(new PropertyValueFactory<>("fechaVariable"));
        columConceptoVariable.setCellValueFactory(new PropertyValueFactory<>("conceptoVariable"));
        columImporteVariable.setCellValueFactory(new PropertyValueFactory<>("gastoVariable"));

        tablaVariable.setItems(miListaCuentaVariable);
    }

    public ObservableList<cuentaFijoBD> miListaCuentaFijo;

    public void mostrarListaCuentasFijo() {
        miListaCuentaFijo = miListaCuentaFijo();

        columFechaFijo.setCellValueFactory(new PropertyValueFactory<>("fechaFijo"));
        columConceptoFijo.setCellValueFactory(new PropertyValueFactory<>("conceptoFijo"));
        columImporteFijo.setCellValueFactory(new PropertyValueFactory<>("gastoFijo"));

        tablaFijo.setItems(miListaCuentaFijo);
    }

    public int cuentaIdVariable;

    public void seleccionarDatosCuentasVariable() {

        cuentaVariableBD cuentaVariableBD = (cuentaVariableBD) tablaVariable.getSelectionModel().getSelectedItem();
        int numero = tablaVariable.getSelectionModel().getSelectedIndex();

        if ((numero - 1) < -1) {
            return;
        }

        cuentaIdVariable = cuentaVariableBD.getId();
        txtVariables.setText(cuentaVariableBD.getGastoVariable().toString());
        txtConcepto.setText(cuentaVariableBD.getConceptoVariable());
        fechaVariable.setValue(LocalDate.parse(String.valueOf(cuentaVariableBD.getFechaVariable())));

    }

    public int cuentaIdFijo;

    public void seleccionarDatosCuentasFijo() {

        cuentaFijoBD cuentaFijoBD = (cuentaFijoBD) tablaFijo.getSelectionModel().getSelectedItem();
        int numero = tablaFijo.getSelectionModel().getSelectedIndex();

        if ((numero - 1) < -1) {
            return;
        }

        cuentaIdFijo = cuentaFijoBD.getId();
        txtFijos.setText(cuentaFijoBD.getGastoFijo().toString());
        txtConceptoFijo.setText(cuentaFijoBD.getConceptoFijo());
        fechaFijo.setValue(LocalDate.parse(String.valueOf(cuentaFijoBD.getFechaFijo())));

    }

    public void borrarDatosBt() {
        txtInicial.setText("");
        txtAdicional.setText("");
        txtFijos.setText("");
        txtConceptoFijo.setText("");
        txtVariables.setText("");
        txtConcepto.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
