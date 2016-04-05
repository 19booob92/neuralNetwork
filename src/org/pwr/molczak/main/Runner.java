package org.pwr.molczak.main;

import java.io.IOException;
import java.util.Scanner;

import org.pwr.molczak.process.Processor;

public class Runner {
	private static final String PATH = "/home/booob/workspace/Chars/images/to_find/";

	public static void main(String[] args) {
		final Processor processor = new Processor(20, 20, 3);

		processor.train();

		while (true) {
			System.out.println("Podaj nazwę pliku: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();

			Character clazz;
			try {
				clazz = processor.findClazz(PATH + input);
				System.err.println(clazz);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}