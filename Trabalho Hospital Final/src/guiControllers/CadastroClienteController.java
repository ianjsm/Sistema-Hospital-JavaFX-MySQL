package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroClienteController {

	@FXML
	private TextField textFieldNome;

	@FXML
	private TextField textFieldIdade;

	@FXML
	private CheckBox checkBoxPlano;

	@FXML
	private TextField textFieldPlano;

	@FXML
	private TextField textFieldSenha;

	@FXML
	private TextField textFieldCPF;

	@FXML
	private Button botaoRealizarCadastroC;

	@FXML
	private Label labelMensagem;

	@FXML
	public void initialize() {
		textFieldPlano.setDisable(true);

		checkBoxPlano.setOnAction(event -> {
			textFieldPlano.setDisable(!checkBoxPlano.isSelected());
			if (!checkBoxPlano.isSelected()) {
				textFieldPlano.clear();
			}
		});
	}

	private boolean cpfExiste() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String cpfDigitado = textFieldCPF.getText();

		String selectQuery = "SELECT * FROM pacientes WHERE cpf = ?";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, cpfDigitado);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@FXML
	public void realizarCadastroP() {

		String nome = textFieldNome.getText();
		String idade = textFieldIdade.getText();
		String senha = textFieldSenha.getText();
		String cpf = textFieldCPF.getText();

		String plano = checkBoxPlano.isSelected() ? textFieldPlano.getText() : null;

		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		if (cpfExiste() == true) {
			Alerts.showAlert("ERRO!", "CPF JÁ EXISTENTE!", "Tente novamente!", AlertType.ERROR);
			return;
		}

		String insertQuery = "INSERT INTO pacientes (cpf, nome, idade, senha, plano) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

			preparedStatement.setString(1, cpf);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, idade);
			preparedStatement.setString(4, senha);
			preparedStatement.setString(5, plano);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				labelMensagem.setText("Cadastro de paciente realizado com sucesso!");
				clearFields();
			} else {
				labelMensagem.setText("Falha ao cadastrar paciente. Tente novamente.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			labelMensagem.setText("Erro ao realizar o cadastro. Tente novamente");
		}
	}

	private void clearFields() {
		textFieldNome.clear();
		textFieldIdade.clear();
		textFieldPlano.clear();
		textFieldSenha.clear();
		textFieldCPF.clear();
	}
}