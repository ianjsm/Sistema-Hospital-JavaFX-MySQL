package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaLoginPacienteController {

	@FXML
	private TextField textFieldUsuario;

	@FXML
	private TextField textFieldSenha;

	@FXML
	private Button botaoLogin;

	@FXML
	private Label labelCliqueCadastro;

	public static String cpfLogado;

	public static String planoLogado;

	public boolean fazerLogin() {
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		String cpf = textFieldUsuario.getText();
		String senha = textFieldSenha.getText();

		String selectQuery = "SELECT * FROM pacientes WHERE cpf = ? AND senha = ?";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, cpf);
			preparedStatement.setString(2, senha);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) { 
					cpfLogado = cpf;
					planoLogado = resultSet.getString("plano");
					abrirMenuPaciente();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Alerts.showAlert("Login inválido", null, "Tente novamente!", AlertType.ERROR);
		return false;
	}

	private void abrirMenuPaciente() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/MenuPaciente.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Menu do paciente");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cliqueBotaoCadastrarPaciente() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/CadastroCliente.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Cadastro de pacientes");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getcpfLogado() {
		return cpfLogado;
	}

	public static String getPlanoLogado() {
		return planoLogado;
	}
}