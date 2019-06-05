package com.number.to.word.NbrToWord;

import org.apache.log4j.Logger;

public class NumberToWordConverter

{

	public static final Logger LOGGER = Logger.getLogger(NumberToWordConverter.class.getName());

	private static final String[] specialNames = { "", " thousand", " million", " billion", " trillion", " quadrillion",
			" quintillion" };

	private static final String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	private String convertLessThanOneThousand(int number) {
		String current = null;
		try {
			if (number % 100 < 20) {
				current = numNames[number % 100];
				number /= 100;
			} else {
				current = numNames[number % 10];
				number /= 10;

				current = tensNames[number % 10] + current;
				number /= 10;
			}
			if (number == 0) {
				return current;
			}
			current = numNames[number] + " hundred" + " and" + current;
		} catch (Exception ex) {
			LOGGER.error("Exception Occured while converting a number to word: ", ex);
		}
		return current;
	}

	public String convert(String nbrInStrFormat) {
		int number = 0;
		if (null != nbrInStrFormat && nbrInStrFormat.contains(",")) {
			number = Integer.parseInt(nbrInStrFormat.replaceAll(",", ""));
		} else {
			number = Integer.parseInt(nbrInStrFormat);
		}
		String resultVal = null;
		try {
			if (number == 0) {
				return "zero";
			}

			String prefix = "";

			if (number < 0) {
				number = -number;
				prefix = "negative";
			}

			String current = "";
			int place = 0;

			do {
				int n = number % 1000;
				if (n != 0) {
					String s = convertLessThanOneThousand(n);
					current = s + specialNames[place] + current;
				}
				place++;
				number /= 1000;
			} while (number > 0);

			resultVal = (prefix + current).trim();
		} catch (Exception ex) {
			LOGGER.error("Exception Occured while converting a number to word: ", ex);
		}
		return resultVal;

	}

	public static void main(String[] args) {
		NumberToWordConverter obj = new NumberToWordConverter();
		String nbrStr = "999,999,999";
		System.out.println("*** " + obj.convert(nbrStr));
	}
}
