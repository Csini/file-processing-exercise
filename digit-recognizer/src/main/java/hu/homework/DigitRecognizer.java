package hu.homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DigitRecognizer {

	public void run(String inputFileName, String outputFileName) throws IOException, URISyntaxException {

//		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
//	     Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
//	 
//	The mapper function passed to flatMap splits a line, using a simple regular expression, into an array of words, and then creates a stream of words from that array.

//		FleinputStream fis;
//		OutputStream out = process(fis);

		Path inputPath = Paths.get(getClass().getClassLoader().getResource(inputFileName).toURI());

		Path outputPath = Paths.get(outputFileName);
//		Files.createDirectories(outputPath.getParent());
		try {
			Files.createFile(outputPath);
		} catch (FileAlreadyExistsException e) {
			// do nothing
		}

//		try (Stream<String> lines = Files.lines(path)) {
//
////			String data = lines.map(line -> {
////				Line l = new Line(line);
////
////				return Arrays.stream(l.getDigits()).parallel().map(digit -> digit.toString())
////						.collect(Collectors.joining(""));
////
////			}).collect(Collectors.joining("\n"));
//			
//			
//			String data = lines.collect(DigitCollector.toCollectedString(Line.SIZE));
//			
//			//TODO
//			System.out.println(data);
//
//		}

		try (Scanner s = new Scanner(inputPath); BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
			s.findAll("(.*\\R){1,4}").map(mr -> {

//				System.out.println(mr.group());

				return new Line(mr.group().split("\\R")).recognize();
			}).forEach(output -> {
//				System.out.println(output);
				try {
					writer.write(output);
					writer.write(System.lineSeparator());
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			});
		}
	}

}
