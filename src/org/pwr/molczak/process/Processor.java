package org.pwr.molczak.process;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.strategy.SmartMomentum;
import org.encog.platformspecific.j2se.data.image.ImageMLData;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.Downsample;
import org.encog.util.downsample.SimpleIntensityDownsample;
import org.encog.util.simple.EncogUtility;
import org.pwr.molczak.model.ClassifiedFile;
import org.pwr.molczak.utils.DataUtils;
import org.pwr.molczak.utils.FileUtils;

public class Processor {

	private static final int HIDDEN_LAYAR_0 = 6;
	private static final int HIDDEN_LAYAR_1 = 0;
	private static final int CLASS_AMOUNT = 3;

	private ImageMLDataSet training;
	private String line;
	private int width;
	private int height;

	private int duration;

	private BasicNetwork network;

	private Downsample downsample;

	private static final Map<Integer, Character> CLASSES = new HashMap<Integer, Character>() {
		{
			put(0, 'A');
			put(0, 'H');
			put(0, 'M');
		}
	};

	public Processor(int width, int height, int duration) {
		this.width = width;
		this.height = height;
		this.duration = duration;

		this.downsample = new SimpleIntensityDownsample();

		this.training = new ImageMLDataSet(this.downsample, false, 1, -1);
		feedWithData();
		createNetwork();
	}

	private void feedWithData() {
		for (ClassifiedFile classifiedFile : DataUtils.FILES) {
			Image image = FileUtils.readFile(classifiedFile.getPath());

			ImageMLData imageData = new ImageMLData(image);
			MLData ideal = new BasicMLData(CLASS_AMOUNT);

			for (int i = 0; i < CLASS_AMOUNT; i++) {
				if (ideal.getClass().equals(classifiedFile.getClazz())) {
					ideal.setData(i, 1);
				} else {
					ideal.setData(i, -1);
				}
			}

			this.training.add(imageData, ideal);
		}
	}

	private void createNetwork() {
		training.downsample(20, 20);
		network = EncogUtility.simpleFeedForward(training.getInputSize(), HIDDEN_LAYAR_0, HIDDEN_LAYAR_1,
				CLASS_AMOUNT, true);
	}

	public void train() {
		Backpropagation train = new Backpropagation(network, training);

		train.addStrategy(new SmartMomentum());

		EncogUtility.trainConsole(train, network, training, duration);

		System.err.println("Finished training");
	}

	public Character findClazz(String path) throws IOException {
		Image image = FileUtils.readFile(path);

		ImageMLData imageToProcess = new ImageMLData(image);

		imageToProcess.downsample(downsample, false, height, width, -1, 1);

		int result = network.winner(imageToProcess);

		return CLASSES.get(result);
	}

}
