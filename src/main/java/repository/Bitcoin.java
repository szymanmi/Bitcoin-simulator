package repository;

import java.util.Random;

public class Bitcoin {
	public static double getBitcoinValue() {
		/*
		TODO:
			value pobierana z internetu
		 */
		Random rand = new Random();

		double value = rand.nextDouble();
		return value;
	}
}
