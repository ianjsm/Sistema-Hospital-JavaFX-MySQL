package guiControllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Consulta;

public class GerarRelatorioMedicoController {

	@FXML
	private ChoiceBox<String> choiceBoxEscolha;

	@FXML
	private Button botaoGerarRelatorio;

	@FXML
	private DatePicker datePickerInicio;

	@FXML
	private DatePicker datePickerFinal;

	@FXML
	private TableView<Consulta> tableViewRelatorio;

	@FXML
	private TableColumn<Consulta, Integer> colunaID;

	@FXML
	private TableColumn<Consulta, String> colunaCPF;

	@FXML
	private TableColumn<Consulta, Date> ColunaData;

	ObservableList<Consulta> lista = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		List<String> opcoes = new ArrayList<>();
		opcoes.add("Consultas");
		opcoes.add("Agendamentos");
		choiceBoxEscolha.getItems().addAll(opcoes);

		colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		ColunaData.setCellValueFactory(new PropertyValueFactory<>("data"));

		tableViewRelatorio.setItems(lista);
	}

	@FXML
	public void cliqueBotaoGerarRelatorio() {
		LocalDate periodoInicio = datePickerInicio.getValue();
		LocalDate periodoFinal = datePickerFinal.getValue();
		Date inicio = java.sql.Date.valueOf(periodoInicio);
		Date fim = java.sql.Date.valueOf(periodoFinal);

		if (choiceBoxEscolha.getValue().equals("Consultas")) {
			String url = "jdbc:mysql://localhost:3306/hospital";
			String username = "developer";
			String password = "86779791";

			String selectQuery = "SELECT * FROM consultas WHERE crm_Medico = ? AND realizada = ? AND dataConsulta BETWEEN ? AND ? ";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

				preparedStatement.setString(1, TelaLoginMedicoController.getcrmLogado());
				preparedStatement.setString(2, "s");
				preparedStatement.setDate(3, inicio);
				preparedStatement.setDate(4, fim);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						int id = resultSet.getInt("idConsulta");
						String cpf = resultSet.getString("cpf_paciente");
						Date data = resultSet.getDate("dataConsulta");
						Consulta consulta = new Consulta(id, cpf, data);
						lista.add(consulta);
					}
					tableViewRelatorio.setItems(lista);
				} catch (SQLException e) {
					e.getMessage();
				}

			} catch (SQLException e) {
				e.getMessage();
			}
		} else if (choiceBoxEscolha.getValue().equals("Agendamentos")) {
			String url = "jdbc:mysql://localhost:3306/hospital";
			String username = "developer";
			String password = "86779791";

			String selectQuery = "SELECT * FROM consultas WHERE crm_Medico = ? AND realizada = ? AND dataConsulta BETWEEN ? AND ? ";
			try (Connection connection = DriverManager.getConnection(url, username, password);
					PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

				preparedStatement.setString(1, TelaLoginMedicoController.getcrmLogado());
				preparedStatement.setString(2, "n");
				preparedStatement.setDate(3, inicio);
				preparedStatement.setDate(4, fim);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						int id = resultSet.getInt("idConsulta");
						String cpf = resultSet.getString("cpf_paciente");
						Date data = resultSet.getDate("dataConsulta");
						Consulta consulta = new Consulta(id, cpf, data);
						lista.add(consulta);
					}
					tableViewRelatorio.setItems(lista);
				} catch (SQLException e) {
					e.getMessage();
				}

			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
}