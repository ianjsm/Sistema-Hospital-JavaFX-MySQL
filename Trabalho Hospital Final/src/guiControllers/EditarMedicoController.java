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

public class EditarMedicoController {

	@FXML
	private RadioButton radioButtonNome;

	@FXML
	private RadioButton radioButtonEspecialidade;

	@FXML
	private RadioButton radioButtonCRM;

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
			String updateQuery = "UPDATE medicoscadastrados " + "SET nome = ? " + "WHERE " + "crm = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginMedicoController.getcrmLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Edição realizada com sucesso!");
				} else {
					labelMensagem.setText("Falha ao editar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao editar.");
			}
			return;
		}

		if (radioButtonEspecialidade.isSelected()) {
			String updateQuery = "UPDATE medicoscadastrados " + "SET especialidade = ? " + "WHERE " + "crm = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginMedicoController.getcrmLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Edição realizada com sucesso!");
				} else {
					labelMensagem.setText("Falha ao editar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar edição.");
			}
			return;
		}
		if (radioButtonPlano.isSelected()) {
			String updateQuery = "UPDATE medicoscadastrados " + "SET plano_atendido = ? " + "WHERE " + "crm = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginMedicoController.getcrmLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Edição realizada com sucesso!");
				} else {
					labelMensagem.setText("Falha ao editar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar edição.");
			}
			return;
		}
		if (radioButtonSenha.isSelected()) {
			String updateQuery = "UPDATE medicoscadastrados " + "SET senha = ? " + "WHERE " + "crm = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginMedicoController.getcrmLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Edição realizada com sucesso!");
				} else {
					labelMensagem.setText("Falha ao editar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar edição.");
			}
			return;
		}
		if (radioButtonCRM.isSelected()) {
			String updateQuery = "UPDATE medicoscadastrados " + "SET crm = ? " + "WHERE " + "crm = ?";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

				preparedStatement.setString(1, dadoNovo);
				preparedStatement.setString(2, TelaLoginMedicoController.getcrmLogado());

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					labelMensagem.setText("Edição realizada com sucesso!");
				} else {
					labelMensagem.setText("Falha ao editar.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				labelMensagem.setText("Erro ao realizar edição.");
			}
			return;
		}
	}
}