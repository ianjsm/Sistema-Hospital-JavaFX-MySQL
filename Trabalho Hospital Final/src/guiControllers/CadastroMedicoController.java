package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroMedicoController {

	@FXML
	private TextField textFieldNome;

	@FXML
	private TextField textFieldEspecialidade;

	@FXML
	private TextField textFieldPlanoAtendido;

	@FXML
	private TextField textFieldSenha;

	@FXML
	private TextField textFieldCRM;

	@FXML
	private Button botaoRealizarCadastro;

	@FXML
	private Label labelMensagem;

	private boolean crmExiste() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String crmDigitado = textFieldCRM.getText();

		String selectQuery = "SELECT * FROM medicoscadastrados WHERE crm = ?";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, crmDigitado);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@FXML
	public void realizarCadastro() {
		String nome = textFieldNome.getText();
		String especialidade = textFieldEspecialidade.getText();
		String planoAtendido = textFieldPlanoAtendido.getText();
		String senha = textFieldSenha.getText();
		String crm = textFieldCRM.getText();

		if (crmExiste() == true) {
			Alerts.showAlert("ERRO!", "CRM JÁ EXISTENTE!", "Tente novamente!", AlertType.ERROR);
			return;
		}

		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		String insertQuery = "INSERT INTO medicoscadastrados (nome, especialidade, plano_atendido, senha, crm)"
				+ " VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, especialidade);
			preparedStatement.setString(3, planoAtendido);
			preparedStatement.setString(4, senha);
			preparedStatement.setString(5, crm);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				labelMensagem.setText("Cadastro de médico realizado com sucesso!");
				clearFields();
			} else {
				labelMensagem.setText("Falha ao cadastrar médico. ");
			}

		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			labelMensagem.setText("Erro ao realizar o cadastro.");
		}
	}

	private void clearFields() {
		textFieldNome.clear();
		textFieldEspecialidade.clear();
		textFieldPlanoAtendido.clear();
		textFieldSenha.clear();
		textFieldCRM.clear();
	}
}