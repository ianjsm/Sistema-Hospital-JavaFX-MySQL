package guiControllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class EscolherConsultaRealizarController {

	@FXML
	private ChoiceBox<String> choiceBoxConsultas;

	@FXML
	private Button botaoEscolha;

	public static String cpfPacienteDaConsulta;

	public static int idConsultaEscolhida;

	public static Date dataEscolhida;

	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String selectQuery = "SELECT * FROM consultas WHERE crm_Medico = ? AND realizada = ?";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, TelaLoginMedicoController.getcrmLogado());
			preparedStatement.setString(2, "n");

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String cpfPaciente = resultSet.getString("cpf_paciente");
					idConsultaEscolhida = resultSet.getInt("idConsulta");
					String data = resultSet.getString("dataConsulta");
					dataEscolhida = resultSet.getDate("dataConsulta");
					String infoConsulta = "CPF do paciente: " + cpfPaciente + " - " + data;
					choiceBoxConsultas.getItems().addAll(infoConsulta);
				}
			} catch (SQLException e) {
				e.getMessage();
			}

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void cliqueBotaoEscolha() {
		String consultaEscolhida = choiceBoxConsultas.getSelectionModel().getSelectedItem();
        String[] parts = consultaEscolhida.split(" - ");

        if (parts.length == 2) {
            String cpf = parts[0].replace("CPF do paciente: ", "");
            String data = parts[1];
            System.out.println("CPF: " + cpf);
            System.out.println("Data: " + data);

            cpfPacienteDaConsulta = cpf;
        }
        
		System.out.println(idConsultaEscolhida);
		System.out.println(cpfPacienteDaConsulta);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/RealizarConsulta.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Consulta");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}