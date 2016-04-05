package org.pwr.molczak.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {
	public static Image readFile(final String path) {
		File imageFile = new File(path);

		Image image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
}
