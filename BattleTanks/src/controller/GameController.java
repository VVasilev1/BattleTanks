package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {
	private static final int BUTTON_IMAGE_WIDTH = 35;
	private static final int BUTTON_IMAGE_HEIGHT = 25;
	private static int yourTanksNumber = 5;
	private static int enemyTanksNumber = 5;
	List<String> enemyTanksPossitions = new ArrayList<>();
	List<String> yourTanksPossitions = new ArrayList<>();
	List<Integer> counter = new ArrayList<>();
	@FXML
	ArrayList<Button> enemyButtons;
	@FXML
	ArrayList<Button> yourButtons;
	List<Button> yourTanks = new ArrayList<Button>();
	@FXML
	static Image image = new Image(Main.class.getResourceAsStream("/Images/Tank.jpg"));
	static Image bomb = new Image(Main.class.getResourceAsStream("/Images/bomb.jpg"));
	static Image groundExplosion = new Image(Main.class.getResourceAsStream("/Images/explosionGround.jpg"));

	public GameController() {
		assignEnemyTanks();
	}

	public void attackedButton(ActionEvent event) {
		Button button = (Button) event.getSource();
		ImageView pic;
		if (enemyTanksPossitions.contains(button.getId())) {
			pic = new ImageView(bomb);
			enemyTanksPossitions.remove(button.getId());
		} else {
			pic = new ImageView(groundExplosion);
		}
		button.setDisable(true);
		pic.setFitHeight(BUTTON_IMAGE_HEIGHT);
		pic.setFitWidth(BUTTON_IMAGE_WIDTH);
		button.setGraphic(pic);
		button.setText("");
		check();
		enemyFire();

	}

	public void placeTank(ActionEvent event) {
		if (yourTanksNumber <= 0) {
			return;
		}
		yourTanksNumber--;
		Button button = (Button) event.getSource();
		ImageView pic = new ImageView(image);
		pic.setFitHeight(25);
		pic.setFitWidth(35);
		button.setGraphic(pic);
		yourTanksPossitions.add(button.getId());
		button.setDisable(true);
		button.setText("");

		if (yourTanksNumber <= 0) {
			for (Button b : enemyButtons) {
				b.setDisable(false);
			}
		}

	}

	public void assignEnemyTanks() {
		Random r = new Random();
		int temp = 0;
		for (int index = enemyTanksNumber; index > 0; index--) {
			temp = r.nextInt(36) + 1;
			if (enemyTanksPossitions.contains(temp + "")) {
				index++;
				continue;
			}
			enemyTanksPossitions.add(temp + "");
		}
	}

	public void check() {
		if (enemyTanksPossitions.isEmpty()) {
			yourTanksNumber = 5;
			enemyTanksNumber = 5;
			Main.setVictoryScene();
		}
		if (yourTanksPossitions.isEmpty()) {
			System.out.println("defeat");
			yourTanksNumber = 5;
			enemyTanksNumber = 5;
			Main.setDefeatScene();
		}

	}

	public void enemyFire() {
		Random r = new Random();
		int temp = 0;
		do {
			temp = r.nextInt(36) + 37;

		} while (counter.contains(temp));
		counter.add(temp);
		Button b = yourButtons.get((temp - 36));
		ImageView pic;
		if (yourTanksPossitions.contains(Integer.toString(temp))) {
			yourTanksPossitions.remove(b.getId());
			pic = new ImageView(bomb);
		} else {
			pic = new ImageView(groundExplosion);
		}
		pic.setFitHeight(BUTTON_IMAGE_HEIGHT);
		pic.setFitWidth(BUTTON_IMAGE_WIDTH);
		b.setText("");
		b.setGraphic(null);
		b.setGraphic(pic);
		b.setDisable(true);

		check();
	}

}
