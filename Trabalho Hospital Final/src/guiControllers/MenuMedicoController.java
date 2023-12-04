package guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuMedicoController {

	@FXML
	private Button botaoEditarMedico;

	@FXML
	public void cliqueBotaoEditarMedico() {
		abrirTelaEditarMedico();
	}

	@FXML
	private Button botaoRealizarConsulta;

	@FXML
	public void cliqueBotaoRealizarConsulta() {
		abrirTelaEscolhaConsulta();
	}

	@FXML
	private Button botaoGerarRelatorio;

	@FXML
	public void cliqueBotaoGerarRelatorio() {
		abrirTelaGerarRelatorio();
	}

	private void abrirTelaEditarMedico() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/EditarMedicos.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Edição de dados dos médicos");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abrirTelaEscolhaConsulta() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/EscolherConsultaRealizar.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Escolha de consulta");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abrirTelaGerarRelatorio() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/GerarRelatorioMedico.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Geração de relatórios");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}