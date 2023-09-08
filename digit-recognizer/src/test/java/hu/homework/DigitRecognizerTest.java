package hu.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DigitRecognizerTest {

	@Test
	public void test_inpit_Q1a() throws IOException, URISyntaxException {
		String outputFileName = "target/output1.txt";
		String inputFileName = "input_Q1a.txt";

		String expectedOutput = "output_Q1a.txt";
		new DigitRecognizer().run(inputFileName, outputFileName);

		Path expectedPath = Paths.get(getClass().getClassLoader().getResource(expectedOutput).toURI());

		Path outputPath = Paths.get(outputFileName);
		Assertions.assertTrue(sameContent(expectedPath, outputPath));
	}

	@Test
	public void test_inpit_Q1b() throws IOException, URISyntaxException {
		String outputFileName = "target/output2.txt";
		String inputFileName = "input_Q1b.txt";

		String expectedOutput = "output_Q1b.txt";
		new DigitRecognizer().run(inputFileName, outputFileName);

		Path expectedPath = Paths.get(getClass().getClassLoader().getResource(expectedOutput).toURI());

		Path outputPath = Paths.get(outputFileName);
		Assertions.assertTrue(sameContent(expectedPath, outputPath));
	}

	boolean sameContent(Path file1, Path file2) throws IOException {
		final long size = Files.size(file1);
		if (size != Files.size(file2)) {
			Assertions.fail("size is not the same");
			return false;
		}

//	    if (size < 4096) {
//	        return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));}

		try (BufferedReader is1 = Files.newBufferedReader(file1); BufferedReader is2 = Files.newBufferedReader(file2)) {
			// Compare line by line
			String data;
			while (is1.ready()) {
				data = is1.readLine();
				String read = is2.readLine();
				if (!data.equals(read)) {
					Assertions.fail("expected: " + data + " but was:" + read);
					return false;
				}
			}
		}

		return true;
	}
}
