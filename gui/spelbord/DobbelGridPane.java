package gui.spelbord;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;

public class DobbelGridPane extends GridPane {

	public DobbelGridPane(Color kleur) {
		super();
		setAlignment(Pos.CENTER);
		setWidth(100);
		Background bg = new Background(new BackgroundFill(kleur, null, null));
		for (int r = 0; r < 8; r++) {
			for (int k = 0; k < 3; k++) {
                Label l = new Label(""+(8-r));
                l.setBackground(bg);
                this.add(l, k, r);
            }
		}		
	}
	
	private int[] getCoordsFromIndex(int index) {
		int[] coords = {index/getRowCount(), index % getColumnCount()};
		return coords;
	}
	private int getIndexFromCoords(int rij, int kolom) {
		assert (kolom < getColumnCount() && rij < getRowCount()); //TODO: fix toegang en "veiligheid"
		return rij*getColumnCount() + kolom;
	};

    public void setCircleColor(int rij ,int kolom, Color kleur) {
    	Label vakje = (Label) getChildren().get(getIndexFromCoords(rij, kolom));
		Background bg = new Background(new BackgroundFill(kleur, null, null));
    	vakje.setBackground(bg);
    }
    
}