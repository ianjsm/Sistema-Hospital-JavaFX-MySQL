package guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuAdminController {

	@FXML
	private Button botaoCadastrarMedico;

	@FXML
	private Button botaoCadastrarPaciente;

	@FXML
	public void cliqueBotaoCadastrarMedico() {
		abrirCadastroMedicos();
	}

	public void cliqueBotaoCadastrarPaciente() {
		abrirCadastroPacientes();
	}

	private void abrirCadastroMedicos() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/CadastroMedicos.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Cadastro de médicos");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abrirCadastroPacientes() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiFXML/CadastroCliente.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Cadastro de pacientes");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}