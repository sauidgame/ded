package gui.spelbord;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utils.GebouwKleur;

/** Klasse voor de zes kolommen naast elkaar op het Spelbord.*/
public class GebouwKolom extends VBox {

	private GebouwKleur kleur;
	private Label ficheVak;
	private GebouwPuntenKolom gebouwPuntenKolom;
	private DobbelGridPane dobbelPane;

	public GebouwKolom(GebouwKleur gebouwKleur) {
		super();
		this.gebouwPuntenKolom = new GebouwPuntenKolom(gebouwKleur);
		this.kleur = gebouwKleur;
		this.ficheVak = new Label("<leeg>");
		this.dobbelPane = new DobbelGridPane(gebouwKleur.getKleur());
		
		//12 gebouwpuntcirkels
		getChildren().add(gebouwPuntenKolom);
		
		//bonus/startspelerfiche vakje
		getChildren().add(ficheVak);
		
		//8x3 vakjes naast elkaar voor resultaten
		getChildren().add(dobbelPane);
		
		setWidth(100);
		setFicheTekst("abcde");
		dobbelPane.setCircleColor(1, 8, Color.DEEPSKYBLUE);
	}
	
	public void setFicheTekst(String tekst) {
		ficheVak.setText(tekst);
	}

}