package gui.spelbord;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * Truc om toch tekstjes "in" Circles te kunnen zetten. Werkt momenteel niet (onzichtbaar in GUI)
 */
public class TextCirclePane extends StackPane {

	private String shortText;
	private Color circleColor;

	public TextCirclePane(String shortText, Color circleColor) {
		super();
		setStyle("-fx-background-color: red;");

		setWidth(50);
		this.shortText = shortText;
		this.circleColor = circleColor;
		
		Text text = new Text(shortText);
		text.setBoundsType(TextBoundsType.VISUAL); 
		Circle circle = new Circle(20, circleColor);
		
		StackPane stack = new StackPane();
		stack.getChildren().addAll(circle, text);
	}

}
