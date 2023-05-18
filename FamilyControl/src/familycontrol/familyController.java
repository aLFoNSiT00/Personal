/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familycontrol;

import familycontrol.agenda;
import familycontrol.CuentasController;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Data
 */
public class familyController implements Initializable {

    @FXML
    private agenda formularioAgendaController;

    @FXML
    private CuentasController formularioCuentasController;

    @FXML
    private Label usuario;

    @FXML
    private Circle circulo;

    @FXML
    private AnchorPane usuarioCard;

    @FXML
    private AnchorPane formularioWeb;

    @FXML
    private WebView webView;

    @FXML
    private WebEngine en;

    @FXML
    private TextField txtwebView;

    @FXML
    private Button btActualizarTareas;

    @FXML
    private Button btAnadirTareas;

    @FXML
    private Button btBorrarTareas;

    @FXML
    private Button btLimpiarTareas;

    @FXML
    private Button btCuentas;

    @FXML
    private TableColumn<tareasBD, String> columComentarios;

    @FXML
    private TableColumn<tareasBD, String> columFechaFin;

    @FXML
    private TableColumn<tareasBD, String> columFechaInicio;

    @FXML
    private TableColumn<tareasBD, String> columTarea;

    @FXML
    private Button btAgenda;

    @FXML
    private Label contadorTareas;

    @FXML
    private Label fechaRegistro;

    @FXML
    private DatePicker fechaTareasFin;

    @FXML
    private DatePicker fechaTareasInicio;

    @FXML
    private AnchorPane formularioInicio;

    @FXML
    private AnchorPane formularioPrincipal;

    @FXML
    private AnchorPane formularioTareas;

    @FXML
    private AnchorPane formularioAgenda;

    @FXML
    private AnchorPane formularioCuentas;

    @FXML
    private Label nombreInicio;

    @FXML
    private AnchorPane numTareas;

    @FXML
    private TableView<tareasBD> tablaTareas;

    @FXML
    private TextArea txtAreaComentarios;

    @FXML
    private TextArea txtAreaTareas;

    @FXML
    private Button btInicio;

    @FXML
    private Button btTareas;

    @FXML
    private Button btWeb;

    @FXML
    private Label tituloFormulario;

    @FXML
    private Button btAnadirAgenda;

    private Connection conexion;
    private PreparedStatement prepare;
    private ResultSet resultado;
    private Statement statement;

    private Alert alerta;

    public void insertaAgenda(ActionEvent event) {

    }

    public void insertarTarea() {

        conexion = baseDatos.conexionBD();
        try {
            if (txtAreaTareas.getText().isEmpty() || txtAreaComentarios.getText().isEmpty() || fechaTareasInicio.getValue() == null || fechaTareasFin.getValue() == null) {

                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensaje de error");
                alerta.setHeaderText(null);;
                alerta.setContentText("Por favor rellene los campos");
                alerta.showAndWait();
            } else {
                if (fechaTareasInicio.getValue().isAfter(fechaTareasFin.getValue())) {
                    alerta = new Alert(AlertType.ERROR);
                    alerta.setTitle("Mensaje de error");
                    alerta.setHeaderText(null);;
                    alerta.setContentText("Fecha errónea");
                    alerta.showAndWait();
                } else {
                    String insertarDatos = "INSERT INTO tareas (tarea, fechaInicio, fechaFin, comentarios) "
                            + "VALUES (?,?,?,?)";
                    prepare = conexion.prepareStatement(insertarDatos);
                    prepare.setString(1, txtAreaTareas.getText());
                    prepare.setString(2, String.valueOf(fechaTareasInicio.getValue().format(DateTimeFormatter.ISO_DATE)));
                    prepare.setString(3, String.valueOf(fechaTareasFin.getValue().format(DateTimeFormatter.ISO_DATE)));
                    prepare.setString(4, txtAreaComentarios.getText());

                    prepare.executeUpdate();

                    mostrarListaTareas();
                    botonLimpiar();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void botonActualizar(ActionEvent event) {

        conexion = baseDatos.conexionBD();

        try {

            if (tareaId == 0) {
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensaje de error");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor seleccione la tarea primero.");
            } else {

                alerta = new Alert(AlertType.CONFIRMATION);
                alerta.setTitle("Mensaja de confirmacion");
                alerta.setHeaderText(null);
                alerta.setContentText("¿Estás eguro que quiere ACTUALIZAR la tarea: "
                        + txtAreaTareas.getText());
                Optional<ButtonType> opcion = alerta.showAndWait();
                if (opcion.get().equals(ButtonType.OK)) {
                    String checkDatos = "SELECT id FROM tareas WHERE id = " + tareaId;

                    statement = conexion.createStatement();
                    resultado = statement.executeQuery(checkDatos);

                    if (resultado.next()) {

                        String actualizaDatos = "UPDATE tareas SET tarea = '"
                                + txtAreaTareas.getText() + "', fechaInicio = '"
                                + fechaTareasInicio.getValue() + "', fechaFin = '"
                                + fechaTareasFin.getValue() + "', comentarios = '"
                                + txtAreaComentarios.getText()
                                + "' WHERE id = " + tareaId;

                        prepare = conexion.prepareStatement(actualizaDatos);
                        prepare.executeUpdate();

                        alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Mensaje de informacion");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Actualizacion realizada");

                        mostrarListaTareas();
                        botonLimpiar();
                    } else {
                        alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Mensaje de error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Actualizacion interrumpida.");

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void botonBorrar() {

        conexion = baseDatos.conexionBD();

        try {

            if (tareaId == 0) {
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensaje de error");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor seleccione la tarea primero.");
            } else {

                alerta = new Alert(AlertType.CONFIRMATION);
                alerta.setTitle("Mensaja de confirmacion");
                alerta.setHeaderText(null);
                alerta.setContentText("¿Estás eguro que quiere BORRAR la tarea: "
                        + txtAreaTareas.getText());
                Optional<ButtonType> opcion = alerta.showAndWait();
                if (opcion.get().equals(ButtonType.OK)) {
                    String checkDatos = "SELECT id FROM tareas WHERE id = " + tareaId;

                    statement = conexion.createStatement();
                    resultado = statement.executeQuery(checkDatos);

                    if (resultado.next()) {
                        String borradoDatos = "DELETE FROM tareas WHERE id = " + tareaId;

                        prepare = conexion.prepareStatement(borradoDatos);
                        prepare.executeUpdate();

                        alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Mensaje de informacion");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Borrado realizado");

                        mostrarListaTareas();
                        botonLimpiar();
                    } else {
                        alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Mensaje de error");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Borrado interrumpido.");

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void botonLimpiar() {
        txtAreaTareas.setText("");
        txtAreaComentarios.setText("");
        fechaTareasInicio.setValue(null);
        fechaTareasFin.setValue(null);
    }

    public ObservableList<tareasBD> miListaTareas() {

        ObservableList<tareasBD> listaTareas = FXCollections.observableArrayList();

        String sentencia = "SELECT * FROM tareas";

        conexion = baseDatos.conexionBD();
        try {

            prepare = conexion.prepareStatement(sentencia);
            resultado = prepare.executeQuery();

            tareasBD datoBD;
            while (resultado.next()) {
                datoBD = new tareasBD(resultado.getInt("id"), resultado.getString("tarea"),
                        resultado.getDate("fechaInicio"), resultado.getDate("fechaFin"), resultado.getString("comentarios"));

                listaTareas.add(datoBD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTareas;
    }

    private ObservableList<tareasBD> miListaTareas;

    private void mostrarListaTareas() {
        miListaTareas = miListaTareas();

        columTarea.setCellValueFactory(new PropertyValueFactory<>("tarea"));
        columFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        columFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        columComentarios.setCellValueFactory(new PropertyValueFactory<>("comentarios"));

        tablaTareas.setItems(miListaTareas);
    }

    private int tareaId;

    public void seleccionDatosTarea() {

        tareasBD tareaBD = tablaTareas.getSelectionModel().getSelectedItem();
        int numero = tablaTareas.getSelectionModel().getSelectedIndex();

        if ((numero - 1) < -1) {
            return;
        }
        tareaId = tareaBD.getId();
        txtAreaTareas.setText(tareaBD.getTarea());
        txtAreaComentarios.setText(tareaBD.getComentarios());
        fechaTareasInicio.setValue(LocalDate.parse(String.valueOf(tareaBD.getFechaInicio())));
        fechaTareasFin.setValue(LocalDate.parse(String.valueOf(tareaBD.getFechaFin())));
    }

    //METODO MOSTRAR NOMBRE DE USUARIO
    public void mostrarUsuario() {

        String user = datos.nombre;
        usuario.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
        nombreInicio.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    //METODO CAMBIO FORMULARIO
    public void cambioFormulario(ActionEvent event) {

        if (event.getSource() == btInicio) {
            usuarioCard.setVisible(true);
            formularioTareas.setVisible(false);
            formularioAgenda.setVisible(false);
            formularioCuentas.setVisible(false);
            tituloFormulario.setText("INICIO");

        } else if (event.getSource() == btTareas) {
            usuarioCard.setVisible(false);
            formularioTareas.setVisible(true);
            formularioAgenda.setVisible(false);
            formularioCuentas.setVisible(false);
            tituloFormulario.setText("MIS TAREAS");

        } else if (event.getSource() == btAgenda) {
            usuarioCard.setVisible(false);
            formularioTareas.setVisible(false);
            formularioAgenda.setVisible(true);
            formularioCuentas.setVisible(false);
            tituloFormulario.setText("AGENDA");
        } else if (event.getSource() == btCuentas) {
            usuarioCard.setVisible(false);
            formularioTareas.setVisible(false);
            formularioAgenda.setVisible(false);
            formularioCuentas.setVisible(true);
            tituloFormulario.setText("AGENDA");
        }
    }

    public void imagenUsuario() {

        FileChooser abrir = new FileChooser();
        Stage stage = (Stage) usuarioCard.getScene().getWindow();

        File fichero = abrir.showOpenDialog(stage);

        if (fichero != null) {

            Image imagen = new Image(fichero.toURI().toString(), 300, 300, false, true);
            circulo.setFill(new ImagePattern(imagen));

        } else {
            System.out.println("El fichero no existe");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mostrarUsuario();
        mostrarListaTareas();
        formularioAgendaController.mostrarListaAgenda();
        formularioCuentasController.mostrarListaCuentasFijo();
        formularioCuentasController.mostrarListaCuentasVariable();

    }

}
