package org.pwr.molczak.kmeans.clustering.main.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public static BufferedImage createImage(BufferedImage originalImage) {

		BufferedImage processedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
				originalImage.getType());
		Graphics2D g = processedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), null);

		return processedImage;
	}

	public static int[] fetchRGBFromImage(BufferedImage img, int height, int width) {
		int[] colors = new int[height * width];
		int iterator = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				colors[iterator] = img.getRGB(x, y);
				iterator++;
			}
		}
		return colors;
	}

	public static void fillImageWItgRgb(int height, int width, BufferedImage resultImage, int[] colors) {
		int iterator = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				resultImage.setRGB(x, y, colors[iterator]);
				iterator++;
			}
		}
	}
}
