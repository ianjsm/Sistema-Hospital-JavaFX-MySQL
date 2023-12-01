package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class EditarPacienteController {

	@FXML
	private RadioButton radioButtonNome;

	@FXML
	private RadioButton radioButtonIdade;

	@FXML
	private RadioButton radioButtonCPF;

	@FXML
	private RadioButton radioButtonPlano;

	@FXML
	private RadioButton radioButtonSenha;

	@FXML
	private TextField textFieldEditar;

	@FXML
	private Button botaoEditar;

	@FXML
	private Label labelMensagem;

	public void editarDado() {
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		String dadoNovo = textFieldEditar.getText();

		if (radioButtonNome.isSelected()) {
			String updateQuery = "UPDATE pacientes " + "SET nome = ? " + "WHERE " + "cpf = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginPacienteController.getcpfLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Cadastro realizado com sucesso!");
				} else {
					labelMensagem.setText("Falha ao cadastrar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar o cadastro.");
			}
			return;
		}

		if (radioButtonIdade.isSelected()) {
			String updateQuery = "UPDATE pacientes " + "SET idade = ? " + "WHERE " + "cpf = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginPacienteController.getcpfLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Cadastro realizado com sucesso!");
				} else {
					labelMensagem.setText("Falha ao cadastrar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar o cadastro.");
			}
			return;
		}
		if (radioButtonPlano.isSelected()) {
			String updateQuery = "UPDATE pacientes " + "SET plano = ? " + "WHERE " + "cpf = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginPacienteController.getcpfLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Cadastro realizado com sucesso!");
				} else {
					labelMensagem.setText("Falha ao cadastrar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar o cadastro.");
			}
			return;
		}
		if (radioButtonSenha.isSelected()) {
			String updateQuery = "UPDATE pacientes " + "SET senha = ? " + "WHERE " + "cpf = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginPacienteController.getcpfLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Cadastro realizado com sucesso!");
				} else {
					labelMensagem.setText("Falha ao cadastrar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar o cadastro.");
			}
			return;
		}
		if (radioButtonCPF.isSelected()) {
			String updateQuery = "UPDATE pacientes " + "SET cpf = ? " + "WHERE " + "cpf = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginPacienteController.getcpfLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Cadastro realizado com sucesso!");
				} else {
					labelMensagem.setText("Falha ao cadastrar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar o cadastro.");
			}
			return;
		}
	}
}