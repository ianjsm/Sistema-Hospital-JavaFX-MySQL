package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CancelarConsultaController {

	@FXML
	private ComboBox<String> comboBoxConsultasMarcadas;

	@FXML
	private Button botaoCancelamento;

	@FXML
	private Label labelMensagem;

	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String selectQuery = "SELECT * FROM consultas WHERE cpf_paciente = ? AND realizada = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			System.out.println(TelaLoginPacienteController.getcpfLogado());
			preparedStatement.setString(1, TelaLoginPacienteController.getcpfLogado());
			preparedStatement.setString(2, "n");

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String numeroConsulta = resultSet.getString("idConsulta");
					String data = resultSet.getString("dataConsulta");
					String infoConsulta = "Nº da consulta: " + numeroConsulta + " - " + data;
					comboBoxConsultasMarcadas.getItems().addAll(infoConsulta);
				}
			} catch (SQLException e) {
				e.getMessage();
			}

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	private String obterCrmMedico(int numeroConsulta) {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String crm = null;

		String selectQuery = "SELECT crm_Medico FROM consultas WHERE idConsulta = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setInt(1, numeroConsulta);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					crm = resultSet.getString("crm_Medico");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return crm;
	}

	public void cliqueBotaoCancelar() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String consultaEscolhida = comboBoxConsultasMarcadas.getValue();
		String numeroDaConsulta = null;
		String[] partes = consultaEscolhida.split(" - ");
		String[] dadosConsulta = partes[0].split(": ");
		numeroDaConsulta = dadosConsulta[1];

		String cpf_paciente_espera = null;

		int numConsultaDesmarcada = Integer.parseInt(numeroDaConsulta);
		String crmMedico = obterCrmMedico(numConsultaDesmarcada);

		String selectQuery = "SELECT cpf_paciente FROM consultasespera WHERE crm_Medico = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, crmMedico);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					cpf_paciente_espera = resultSet.getString("cpf_paciente");
					System.out.println("cpf nao nulo ");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (SQLException e) {
			e.getMessage();
		}

		String deleteQueryEspera = "DELETE FROM consultasespera WHERE crm_Medico = ? AND cpf_paciente = ? ORDER BY idEspera LIMIT 1";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(deleteQueryEspera)) {

			preparedStatement.setString(1, crmMedico);
			preparedStatement.setString(2, cpf_paciente_espera);

			int rowsAffected1 = preparedStatement.executeUpdate();

			if (rowsAffected1 > 0) {
				System.out.println("CONSULTAS ESPERA APAGADO C SUCESSO");
			} else {
				System.out.println("CONSULTAS ESPERA NAO APAGADO");
			}
		} catch (SQLException e) {
			e.getMessage();
		}

		String updateQuery = "UPDATE consultas SET cpf_paciente = ? WHERE idConsulta = ?";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			preparedStatement.setString(1, cpf_paciente_espera);
			preparedStatement.setInt(2, numConsultaDesmarcada);

			int rowsAffected2 = preparedStatement.executeUpdate();

			if (rowsAffected2 > 0) {
				labelMensagem.setText("Consulta desmarcada com sucesso!");
				System.out.println("UPDATE SIMMMMMMMMM RELAIAOD");
			} else {
				labelMensagem.setText("Não foi possível desmarcar a consulta. Tente novamente");
				System.out.println("UPDATE NAAAAAAAO REALIADO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}