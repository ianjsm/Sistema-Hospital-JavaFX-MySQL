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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginMedicoController {

	@FXML
	private TextField textFieldUsuario;

	@FXML
	private TextField textFieldSenha;

	@FXML
	private Button botaoLogin;

	@FXML
	private Label labelCliqueCadastro;

	public static String crmLogado;

	public boolean fazerLogin() {
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		String crm = textFieldUsuario.getText();
		String senha = textFieldSenha.getText();

		String selectQuery = "SELECT * FROM medicoscadastrados WHERE senha = ? AND crm = ?";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, senha);
			preparedStatement.setString(2, crm);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					crmLogado = crm;
					abrirMenuMedico();
					return true;
				} else {
					Alerts.showAlert("Login inválido", null, "Usuário não encontrado", AlertType.ERROR);
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Alerts.showAlert("Erro", null, "Erro ao realizar o login", AlertType.ERROR);
			return false;
		}
	}

	public void cliqueBotaoCadastrarMedico() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/CadastroMedicos.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Cadastro de médicos");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abrirMenuMedico() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/MenuMedico.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Menu do médico");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getcrmLogado() {
		return crmLogado;
	}
}