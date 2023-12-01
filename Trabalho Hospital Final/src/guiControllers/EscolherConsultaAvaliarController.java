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

public class EscolherConsultaAvaliarController {

	@FXML
	private ChoiceBox<String> choiceBoxConsultas;

	@FXML
	private Button botaoEscolha;

	public static int idConsultaEscolhida;

	private String StringIdConsultaEscolhida;;

	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
		String username = "developer";
		String password = "86779791";

		String selectQuery = "SELECT * FROM consultasrealizadas WHERE cpf_paciente = ? AND estrelas = ?";
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, TelaLoginPacienteController.getcpfLogado());
			preparedStatement.setDouble(2, 0);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int idConsulta = resultSet.getInt("idConsulta");
					Date dataConsulta = resultSet.getDate("dataConsulta");
					String infoConsulta = "Nº consulta: " + idConsulta + " - " + "Data: " + dataConsulta;
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
		String consultaEscolhida = choiceBoxConsultas.getValue();
		String[] parte = consultaEscolhida.split(": ");

		if (parte.length >= 2) {
			String[] partes = parte[1].split(" ");
			if (partes.length > 0) {
				StringIdConsultaEscolhida = partes[0];
			}
		}
		idConsultaEscolhida = Integer.parseInt(StringIdConsultaEscolhida);
		System.out.println("CONSULTA FOI ESCOLHIDA C SUCESSO");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/AvaliarMedico.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Avaliação do médico");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}