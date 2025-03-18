package ui.cui;

import ui.MessagerInterface;

public class CUIMessager implements MessagerInterface {

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

}
