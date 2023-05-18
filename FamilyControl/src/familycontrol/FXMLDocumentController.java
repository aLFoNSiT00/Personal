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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Data
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane formularioInicio;

    @FXML
    private AnchorPane formularioPrincipal;

    @FXML
    private AnchorPane formularioRegistro;

    @FXML
    private Button loginBt;

    @FXML
    private TextField loginNombre;

    @FXML
    private Hyperlink loginOff;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button registroBt;

    @FXML
    private TextField registroNombre;

    @FXML
    private Hyperlink registroOk;

    @FXML
    private PasswordField registroPassword;

    private Connection conexion;
    private PreparedStatement prepare;
    private ResultSet resultado;

    private Alert alerta;
    private Alert altaUsuario;
    

    @FXML
    private void cuentaUsuario(ActionEvent event) {

        String selectInfo = "SELECT nombre, password FROM usuarios WHERE nombre = '"
                + loginNombre.getText() + "' AND password = '" + loginPassword.getText() + "'";

        conexion = baseDatos.conexionBD();

        try {

            if (loginNombre.getText().isEmpty() || loginPassword.getText().isEmpty()) {
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensaje de error");
                alerta.setTitle(null);
                alerta.setContentText("Por favor rellene todos los espacios en blanco");
                alerta.showAndWait();
            } else {
                prepare = conexion.prepareStatement(selectInfo);
                resultado = prepare.executeQuery();

                if (resultado.next()) {

                    datos.nombre = loginNombre.getText();

                    alerta = new Alert(AlertType.INFORMATION);
                    alerta.setTitle("Mensaje de información");
                    alerta.setTitle(null);
                    alerta.setContentText("Sesión iniciada con éxito");
                    alerta.showAndWait();

                    //Ocultamos la escena de registro/logeo
                    loginBt.getScene().getWindow().hide();

                    //Mostramos la escena principal
                    Parent root = FXMLLoader.load(getClass().getResource("familyController.fxml"));
                    Stage stage = new Stage();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Mensaje de error");
                    alerta.setTitle(null);
                    alerta.setContentText("Usuario o password incorrecto");
                    alerta.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registroUsuario(ActionEvent event) {

        String insertarDatos = "INSERT INTO usuarios (nombre, password, date) VALUES (?,?,?)";
        conexion = baseDatos.conexionBD();
        try {

            if (registroNombre.getText().isEmpty() || registroPassword.getText().isEmpty()) {
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensaje de error");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor rellene todos los espacios en blanco");
                alerta.showAndWait();
            } else {

                String comprobarNombre = "SELECT nombre FROM usuarios WHERE nombre = '"
                        + registroNombre.getText() + "'";

                prepare = conexion.prepareStatement(comprobarNombre);
                resultado = prepare.executeQuery();

                if (resultado.next()) {
                    alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Mensaje de error");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Ya existe un usuario con el nombre " + registroNombre.getText());
                    alerta.showAndWait();
                } else {

                    if (registroPassword.getText().length() < 8) {
                        alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Mensaje de error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Contraseña errónea, 8 caracteres mínimo");
                        alerta.showAndWait();
                    } else {
                        prepare = conexion.prepareStatement(insertarDatos);
                        prepare.setString(1, registroNombre.getText());
                        prepare.setString(2, registroPassword.getText());

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare.setString(3, String.valueOf(sqlDate));

                        prepare.executeUpdate();

                        altaUsuario = new Alert(AlertType.INFORMATION);
                        altaUsuario.setTitle("Mensaje de confirmación");
                        altaUsuario.setHeaderText(null);
                        altaUsuario.setContentText("Usuario registrado con éxito");
                        altaUsuario.showAndWait();

                        registroNombre.setText("");
                        registroPassword.setText("");

                        formularioRegistro.setVisible(false);
                        formularioInicio.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambioFormulario(ActionEvent event) {
        if (event.getSource() == loginOff) {
            formularioRegistro.setVisible(true);
            formularioInicio.setVisible(false);
        } else if (event.getSource() == registroOk) {
            formularioRegistro.setVisible(false);
            formularioInicio.setVisible(true);
        }
    }
    
    public void salir(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
