package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;

public class AgendarConsultaController {

	@FXML
	private ComboBox<String> comboBoxMedicosDisponiveis;

	@FXML
	private DatePicker datePickerDatas;

	@FXML
	private Button botaoAgendarConsulta;

	@FXML
	private Label labelMensagem;

	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		if (TelaLoginPacienteController.getPlanoLogado() != null) {
			String selectQuery = "SELECT * FROM medicoscadastrados WHERE plano_atendido = ? ";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

				preparedStatement.setString(1, TelaLoginPacienteController.getPlanoLogado());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						String nome = resultSet.getString("nome");
						String especialidade = resultSet.getString("especialidade");
						String nomeEspecialidade = nome + " - " + especialidade;
						comboBoxMedicosDisponiveis.getItems().addAll(nomeEspecialidade);
					}
				} catch (SQLException e) {
					e.getMessage();
				}

			} catch (SQLException e) {
				e.getMessage();
			}
		} else {
			String selectQuery = "SELECT * FROM medicoscadastrados";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						String nome = resultSet.getString("nome");
						String especialidade = resultSet.getString("especialidade");
						String nomeEspecialidade = nome + " - " + especialidade;
						comboBoxMedicosDisponiveis.getItems().addAll(nomeEspecialidade);
					}
				} catch (SQLException e) {
					e.getMessage();
				}
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}

		datePickerDatas.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(DatePicker param) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
							setStyle("-fx-background-color: #D3D3D3;");
						}
					}
				};
			}
		});
	}

	public boolean medicoDisponivel(String nomeMedico, java.sql.Date dataEscolhida) {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";
		String crm = null;

		String selectQuery = "SELECT * FROM medicoscadastrados WHERE nome = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, nomeMedico);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					crm = resultSet.getString("crm");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String countQuery = "SELECT COUNT(*) AS contador FROM consultas WHERE crm_Medico = ? AND dataConsulta = ? AND realizada = ?";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(countQuery)) {

			preparedStatement.setString(1, crm);
			preparedStatement.setDate(2, dataEscolhida);
			preparedStatement.setString(3, "n");

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int numeroConsultas = resultSet.getInt("contador");
					if (numeroConsultas >= 4) {
						return false;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void cliqueBotaoAgendamento() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String medicoEscolhido = comboBoxMedicosDisponiveis.getValue();
		String[] partes = medicoEscolhido.split(" - ");
		String nomeMedico = partes[0];

		LocalDate dataDatePicker = datePickerDatas.getValue();
		java.sql.Date dataEscolhida = java.sql.Date.valueOf(dataDatePicker);

		String crmMedico = null;
		String cpfPaciente = TelaLoginPacienteController.getcpfLogado();

		String selectQuery = "SELECT * FROM medicoscadastrados WHERE nome = ? ";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, nomeMedico);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					crmMedico = resultSet.getString("crm");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (SQLException e) {
			e.getMessage();
		}

		if (medicoDisponivel(nomeMedico, dataEscolhida) == false) {

			String insertQuery = "INSERT INTO consultasespera (crm_Medico, cpf_paciente, dataConsulta) VALUES (?, ?, ?)";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

				preparedStatement.setString(1, crmMedico);
				preparedStatement.setString(2, cpfPaciente);
				preparedStatement.setDate(3, dataEscolhida);

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("ADD A ESPERA");
					Alerts.showAlert("Agenda lotada!", null,
							"A agenda desse médico está lotada hoje! Você foi adicionado à lista de espera.",
							AlertType.INFORMATION);
				} else {
					System.out.println("NAO ADD A ESPERA");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("NAO ADD A ESPERA SQL EXCEPTION");
			}
			comboBoxMedicosDisponiveis.setValue(null);
			datePickerDatas.setValue(null);
			return;
		}

		String insertQuery = "INSERT INTO consultas (crm_Medico, cpf_paciente, dataConsulta, realizada) VALUES (?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

			preparedStatement.setString(1, crmMedico);
			preparedStatement.setString(2, cpfPaciente);
			preparedStatement.setDate(3, dataEscolhida);
			preparedStatement.setString(4, "n");

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				comboBoxMedicosDisponiveis.setValue(null);
				datePickerDatas.setValue(null);
				labelMensagem.setText("Agendamento realizado com sucesso!");
			} else {
				comboBoxMedicosDisponiveis.setValue(null);
				datePickerDatas.setValue(null);
				labelMensagem.setText("Falha ao agendar consulta. Tente novamente.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			comboBoxMedicosDisponiveis.setValue(null);
			datePickerDatas.setValue(null);
			labelMensagem.setText("Erro ao realizar agendamento. Tente novamente");
		}
	}
}