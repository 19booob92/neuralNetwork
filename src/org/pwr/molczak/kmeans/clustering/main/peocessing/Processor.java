package org.pwr.molczak.kmeans.clustering.main.peocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pwr.molczak.kmeans.clustering.main.utils.ImageUtils;
import org.pwr.molczak.kmeans.clustering.main.utils.KMeansAlgorithm;
import org.pwr.molczak.kmeans.clustering.main.utils.Toggle;

public class Processor {

	private int[] colors;

	public BufferedImage readImageAsMatrix(String path, int clusterAmount) throws IOException {
		File imageFile = new File(path);
		BufferedImage img = ImageIO.read(imageFile);

		int height = img.getHeight();
		int width = img.getWidth();

		BufferedImage resultImage = ImageUtils.createImage(img);

		colors = ImageUtils.fetchRGBFromImage(resultImage, height, width);

		KMeansAlgorithm.execute(colors, clusterAmount, Toggle.OFF);

		ImageUtils.fillImageWItgRgb(height, width, resultImage, colors);

		return resultImage;
	}

}
