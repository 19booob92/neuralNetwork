package org.pwr.molczak.kmeans.clustering.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pwr.molczak.kmeans.clustering.main.peocessing.Processor;

public class Runner {

	private static final int CLUSTER_AMOOUNT = 3;

	public static void main(String[] args) {

		// IplImage origImg =
		// cvLoadImage("/home/booob/workspace/GenderRecocnizer/faces_to_test/lena.jpg",
		// IPL_DEPTH_32S);
		//
		// CvMat model = new CvMat(new Mat(origImg));
		//
		// // Number of cluster
		// int k = CLUSTER_AMOOUNT;
		// CvMat cluster = model.clone();
		//
		// System.err.println(""+ model.type());
		// System.err.println(cluster.type());
		//
		// cvKMeans2(model, k, cluster, cvTermCriteria(CV_TERMCRIT_EPS +
		// CV_TERMCRIT_ITER, 10, 1.0));
		//
		// CanvasFrame canvas = new CanvasFrame("Rezultat", 1);
		// canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// final OpenCVFrameConverter.ToIplImage converter = new
		// OpenCVFrameConverter.ToIplImage();
		// canvas.showImage(converter.convert(origImg));

		Processor processor = new Processor();
		try {
			BufferedImage result = processor
					.readImageAsMatrix("/home/booob/workspace/GenderRecocnizer/faces_to_test/2.jpg", CLUSTER_AMOOUNT);
			ImageIO.write(result, "png", new File("/home/booob/workspace/GenderRecocnizer/faces_to_test/modified.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
