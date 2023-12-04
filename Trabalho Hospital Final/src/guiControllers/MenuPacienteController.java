package guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPacienteController {

	@FXML
	private Button botaoEditarDados;

	@FXML
	private Button botaoAgendarConsulta;

	@FXML
	private Button botaoCancelarConsulta;

	@FXML
	private Button botaoPesquisarMedicos;

	@FXML
	private Button botaoGerarRelatorios;

	@FXML
	private Button botaoAvaliarConsulta;

	public void cliqueBotaoAgendarConsulta() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/AgendarConsulta.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Agendar consulta");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cliqueBotaoCancelarConsulta() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/CancelarConsulta.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Cancelar consulta");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cliqueBotaoEditarDados() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/EditarPaciente.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Edição de dados do paciente");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cliqueBotaoPesquisarMedicos() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/PesquisarMedicos.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Pesquisar médicos");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void botaoGerarRelatoriosPaciente() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/GerarRelatorioPaciente.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Gerar relatórios");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cliqueBotaoAvaliarConsulta() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/EscolherConsultaAvaliar.fxml"));
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