package cui;

import java.util.Scanner;

import domein.DomeinController;
import utils.Vertaler;

public class StartUp {

	public static void main(String[] args) {

		Scanner invoer = new Scanner(System.in);
		Vertaler.initialiseer();
		new SpelApplicatie(new DomeinController(), invoer).start();
		invoer.close();
		
	}
}