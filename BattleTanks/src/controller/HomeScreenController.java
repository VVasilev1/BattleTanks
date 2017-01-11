package controller;

import application.Main;
import javafx.fxml.FXML;

public class HomeScreenController {

	private Main main;
	@FXML
	public void startGame () {
		Main.setGameScene();
	}
	
	public void instructions () {
		Main.setInstructionScene();
	}
	
}
