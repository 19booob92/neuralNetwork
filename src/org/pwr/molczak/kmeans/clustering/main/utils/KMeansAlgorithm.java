package org.pwr.molczak.kmeans.clustering.main.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class KMeansAlgorithm {
	private static Map<Integer, Integer> values = new HashMap<>();

	public static void execute(int colors[], int clusterAmounts, Toggle toggle) {
		int[] previousCentroids = new int[clusterAmounts];
		int[] currentCentroids = new int[clusterAmounts];
		int[] noOfPixels = new int[clusterAmounts];
		int[] noOfRedInCluster = new int[clusterAmounts];
		int[] noOfGreenInCluster = new int[clusterAmounts];
		int[] noOfBlueInCluster = new int[clusterAmounts];
		int[] clusterAssignment = new int[colors.length];

		double maximumDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int closestCenter = 0;

		for (int x = 0; x < clusterAmounts; x++) {
			Random random = new Random();
			int centerValue = 0;
			centerValue = colors[random.nextInt(colors.length)];
			currentCentroids[x] = centerValue;
		}

		do {
			for (int x = 0; x < currentCentroids.length; x++) {
				previousCentroids[x] = currentCentroids[x];
				noOfPixels[x] = 0;
				noOfRedInCluster[x] = 0;
				noOfGreenInCluster[x] = 0;
				noOfBlueInCluster[x] = 0;
			}

			for (int x = 0; x < colors.length; x++) {
				maximumDistance = Double.MAX_VALUE;

				for (int y = 0; y < currentCentroids.length; y++) {
					currentDistance = calculatePixelDistance(colors[x], currentCentroids[y]);
					if (currentDistance < maximumDistance) {
						maximumDistance = currentDistance;
						closestCenter = y;
					}
				}

				clusterAssignment[x] = closestCenter;
				noOfPixels[closestCenter]++;
				noOfRedInCluster[closestCenter] += ((colors[x] & 0x00FF0000) >>> 16);
				noOfGreenInCluster[closestCenter] += ((colors[x] & 0x0000FF00) >>> 8);
				noOfBlueInCluster[closestCenter] += ((colors[x] & 0x000000FF) >>> 0);
			}

			for (int x = 0; x < currentCentroids.length; x++) {
				int averageOfRed = (int) ((double) noOfRedInCluster[x] / (double) noOfPixels[x]);
				int averageOfGreen = (int) ((double) noOfGreenInCluster[x] / (double) noOfPixels[x]);
				int averageOfBlue = (int) ((double) noOfBlueInCluster[x] / (double) noOfPixels[x]);

				currentCentroids[x] = ((averageOfRed & 0x000000FF) << 16) | ((averageOfGreen & 0x000000FF) << 8)
						| ((averageOfBlue & 0x000000FF) << 0);
			}
		} while (!isConverged(previousCentroids, currentCentroids));

		if (Toggle.ON.equals(toggle)) {
			for (int x = 0; x < colors.length; x++) {
				colors[x] = currentCentroids[clusterAssignment[x]];
			}
		} else {
			for (int x = 0; x < colors.length; x++) {
				int value = currentCentroids[clusterAssignment[x]];
				Integer actualValue = values.get(value);
				if (actualValue == null) {
					values.put(value, 1);
				} else {
					values.put(value, ++actualValue);
				}
			}

			int iterator = 0;
			for (int key : values.keySet()) {
				for (int i = 0; i < values.get(key); i++) {
					colors[iterator] = key;
					iterator++;
				}
			}
		}
	}

	private static boolean isConverged(int[] previousCentroids, int[] currentCentroids) {
		for (int x = 0; x < currentCentroids.length; x++)
			if (previousCentroids[x] != currentCentroids[x])
				return false;

		return true;
	}

	private static double calculatePixelDistance(int pixelA, int pixelB) {
		int differenceOfRed = ((pixelA & 0x00FF0000) >>> 16) - ((pixelB & 0x00FF0000) >>> 16);
		int differenceOfGreen = ((pixelA & 0x0000FF00) >>> 8) - ((pixelB & 0x0000FF00) >>> 8);
		int differenceOfBlue = ((pixelA & 0x000000FF) >>> 0) - ((pixelB & 0x000000FF) >>> 0);
		return Math.sqrt(differenceOfRed * differenceOfRed + differenceOfGreen * differenceOfGreen
				+ differenceOfBlue * differenceOfBlue);
	}
}
