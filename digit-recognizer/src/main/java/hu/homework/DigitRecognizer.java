package hu.homework;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import hu.homework.recognize.Line;

public class DigitRecognizer {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		run(args[0], args[1]);
	}

	public static void run(String inputFileName, String outputFileName) throws IOException, URISyntaxException {

		Path inputPath = Paths.get(DigitRecognizer.class.getClassLoader().getResource(inputFileName).toURI());

		Path outputPath = Paths.get(outputFileName);
		
		try {
			Files.createFile(outputPath);
		} catch (FileAlreadyExistsException e) {
			// do nothing
			//maybe ask to really owerwrite the existing file
			
			System.out.print("Are you sure, that you want to override the existing file '"+outputFileName+"'? (y/n) ");  
			Scanner sc= new Scanner(System.in);   
			String answer= sc.nextLine();
			if(!"y".equals(answer.trim().toLowerCase())) {
				System.out.println("\texiting without processing '"+inputFileName+"'");
				return;
			}else {
				System.out.println("\toverriding...");
			}
		}

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
