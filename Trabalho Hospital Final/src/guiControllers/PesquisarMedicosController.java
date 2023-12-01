package guiControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PesquisarMedicosController {

	@FXML
	private ChoiceBox<String> choiceBoxEscolha;

	@FXML
	private TextField textFieldDado;

	@FXML
	private Button botaoPesquisar;

	@FXML
	private ListView<String> listViewResultados;

	@FXML
	public void initialize() {
		choiceBoxEscolha.getItems().setAll("Nome", "Especialidade");
	}

	public void procurarMedicos() {
		String dado = textFieldDado.getText();
		String selectQuery = null;

		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		Connection connection = null;
		try {
			if (TelaLoginPacienteController.getPlanoLogado() == null) {
				System.out.println("NULO");
				selectQuery = "SELECT crm_Medico, AVG(estrelas) AS notaMedia FROM consultasrealizadas GROUP BY crm_Medico";

				connection = DriverManager.getConnection(url, username, password);

				Map<String, Double> notaMediaMap = new HashMap<>();

				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						String crmMedico = resultSet.getString("crm_Medico");
						double notaMedia = resultSet.getDouble("notaMedia");

						// Store average ratings in the map using doctor's CRM as the key
						notaMediaMap.put(crmMedico, notaMedia);
					}
				}

				String selectDetailsQuery = "SELECT * FROM medicoscadastrados WHERE nome = ? OR especialidade = ?";
				try (PreparedStatement preparedStatementDetails = connection.prepareStatement(selectDetailsQuery)) {
					preparedStatementDetails.setString(1, dado);
					preparedStatementDetails.setString(2, dado);

					try (ResultSet resultSetDetails = preparedStatementDetails.executeQuery()) {
						List<String> listaMedicos = new ArrayList<>();
						while (resultSetDetails.next()) {
							String crm = resultSetDetails.getString("crm");
							String nomeMedico = resultSetDetails.getString("nome");
							String especialidadeMedico = resultSetDetails.getString("especialidade");

							// Get the average rating from the map using the crm_Medico
							double notaMedia = notaMediaMap.getOrDefault(crm, 0.0);
							String StringNotaMedia = String.format("%.1f", notaMedia);
							String nomeEspecialidade = nomeMedico + " - " + especialidadeMedico + " - "
									+ StringNotaMedia;

							listaMedicos.add(nomeEspecialidade);
						}
						listViewResultados.getItems().setAll(listaMedicos);
					} catch (SQLException e) {
						e.printStackTrace();
						// alert erro
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}  else if (choiceBoxEscolha.getValue().equals("Nome")) {
				selectQuery = "SELECT crm_Medico, AVG(estrelas) AS notaMedia FROM consultasrealizadas GROUP BY crm_Medico";

				connection = DriverManager.getConnection(url, username, password);

				Map<String, Double> notaMediaMap = new HashMap<>();

				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						String crmMedico = resultSet.getString("crm_Medico");
						double notaMedia = resultSet.getDouble("notaMedia");

						// Store average ratings in the map using doctor's CRM as the key
						notaMediaMap.put(crmMedico, notaMedia);
					}
				}

				String selectDetailsQuery = "SELECT * FROM medicoscadastrados WHERE plano_atendido = ? AND nome = ? ";
				try (PreparedStatement preparedStatementDetails = connection.prepareStatement(selectDetailsQuery)) {
					preparedStatementDetails.setString(1, TelaLoginPacienteController.getPlanoLogado());
					preparedStatementDetails.setString(2, dado);

					try (ResultSet resultSetDetails = preparedStatementDetails.executeQuery()) {
						List<String> listaMedicos = new ArrayList<>();
						while (resultSetDetails.next()) {
							String crm = resultSetDetails.getString("crm");
							String nomeMedico = resultSetDetails.getString("nome");
							String especialidadeMedico = resultSetDetails.getString("especialidade");

							// Get the average rating from the map using the crm_Medico
							double notaMedia = notaMediaMap.getOrDefault(crm, 0.0);
							String StringNotaMedia = String.format("%.1f", notaMedia);
							String nomeEspecialidade = nomeMedico + " - " + especialidadeMedico + " - "
									+ StringNotaMedia;

							listaMedicos.add(nomeEspecialidade);
						}
						listViewResultados.getItems().setAll(listaMedicos);
					} catch (SQLException e) {
						e.printStackTrace();
						// alert erro
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (choiceBoxEscolha.getValue().equals("Especialidade")) {
				selectQuery = "SELECT crm_Medico, AVG(estrelas) AS notaMedia FROM consultasrealizadas GROUP BY crm_Medico";

				connection = DriverManager.getConnection(url, username, password);

				Map<String, Double> notaMediaMap = new HashMap<>();

				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						String crmMedico = resultSet.getString("crm_Medico");
						double notaMedia = resultSet.getDouble("notaMedia");

						// Store average ratings in the map using doctor's CRM as the key
						notaMediaMap.put(crmMedico, notaMedia);
					}
				}

				String selectDetailsQuery = "SELECT * FROM medicoscadastrados WHERE plano_atendido = ? AND especialidade = ? ";
				try (PreparedStatement preparedStatementDetails = connection.prepareStatement(selectDetailsQuery)) {
					preparedStatementDetails.setString(1, TelaLoginPacienteController.getPlanoLogado());
					preparedStatementDetails.setString(2, dado);

					try (ResultSet resultSetDetails = preparedStatementDetails.executeQuery()) {
						List<String> listaMedicos = new ArrayList<>();
						while (resultSetDetails.next()) {
							String crm = resultSetDetails.getString("crm");
							String nomeMedico = resultSetDetails.getString("nome");
							String especialidadeMedico = resultSetDetails.getString("especialidade");

							// Get the average rating from the map using the crm_Medico
							double notaMedia = notaMediaMap.getOrDefault(crm, 0.0);
							String StringNotaMedia = String.format("%.1f", notaMedia);
							String nomeEspecialidade = nomeMedico + " - " + especialidadeMedico + " - "
									+ StringNotaMedia;

							listaMedicos.add(nomeEspecialidade);
						}
						listViewResultados.getItems().setAll(listaMedicos);
					} catch (SQLException e) {
						e.printStackTrace();
						// alert erro
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection in the finally block
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}