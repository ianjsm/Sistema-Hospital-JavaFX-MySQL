package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RealizarConsultaController {

	@FXML
	private TextArea textAreaSintomas;

	@FXML
	private TextArea textAreaTratamento;

	@FXML
	private TextArea textAreaMedicamentos;

	@FXML
	private TextArea textAreaExames;

	@FXML
	private TextField textFieldPreco;

	@FXML
	private Button botaoFinalizarConsulta;

	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		String planoDoPaciente = null;

		String selectQuery = "SELECT plano FROM pacientes WHERE cpf_paciente = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, EscolherConsultaRealizarController.cpfPacienteDaConsulta);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					planoDoPaciente = resultSet.getString("plano");
				}
			} catch (SQLException e) {
				e.getMessage();
			}

		} catch (SQLException e) {
			e.getMessage();
		}

		if (planoDoPaciente == null) {
			textFieldPreco.setDisable(false);
		} else {
			textFieldPreco.setDisable(true);
		}
	}

	public void cliqueBotaoFinalizarConsulta() {
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "developer";
		String password = "86779791";

		String planoPaciente = null;

		String selectQuery = "SELECT plano FROM pacientes WHERE cpf_paciente = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, EscolherConsultaRealizarController.cpfPacienteDaConsulta);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					planoPaciente = resultSet.getString("plano");
				}
			} catch (SQLException e) {
				e.getMessage();
			}

		} catch (SQLException e) {
			e.getMessage();
		}

		double valorConsulta;

		if (planoPaciente == null) {
			String preco = textFieldPreco.getText();
			valorConsulta = Double.parseDouble(preco);
		} else {
			valorConsulta = 0;
		}

		String sintomas = textAreaSintomas.getText();
		String tratamento = textAreaTratamento.getText();
		String medicamentos = textAreaMedicamentos.getText();
		String exames = textAreaExames.getText();

		String insertQuery = "INSERT INTO consultasrealizadas (idConsulta, crm_Medico, cpf_paciente, dataConsulta, valorConsulta, textoAvaliacao, estrelas, sintomas, tratamento, exames, medicamentos) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

			preparedStatement.setInt(1, EscolherConsultaRealizarController.idConsultaEscolhida);
			preparedStatement.setString(2, TelaLoginMedicoController.getcrmLogado());
			preparedStatement.setString(3, EscolherConsultaRealizarController.cpfPacienteDaConsulta);
			preparedStatement.setDate(4, EscolherConsultaRealizarController.dataEscolhida);
			preparedStatement.setDouble(5, valorConsulta);
			preparedStatement.setString(6, "");
			preparedStatement.setDouble(7, 0);
			preparedStatement.setString(8, sintomas);
			preparedStatement.setString(9, tratamento);
			preparedStatement.setString(10, exames);
			preparedStatement.setString(11, medicamentos);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("cosnulta add as realizas");
				clearFields();
			} else {
				System.out.println("consultas naaaaaaaao add as realizasd");
				clearFields();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String updateQuery = "UPDATE consultas SET realizada = ? WHERE idConsulta = ? ";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			preparedStatement.setString(1, "s");
			preparedStatement.setInt(2, EscolherConsultaRealizarController.idConsultaEscolhida);

			int rowsAffected5 = preparedStatement.executeUpdate();

			if (rowsAffected5 > 0) {
				System.out.println("CONSULTA DELETADA FEITA C SUCESSO");
			} else {
				System.out.println("CONSULTAS NAO DELATADAAAA FEITAAAAAAAAAA");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	private void clearFields() {
		textAreaSintomas.setText(null);
		textAreaTratamento.setText(null);
		textAreaMedicamentos.setText(null);
		textAreaExames.setText(null);
		textFieldPreco.setText("");
	}
}