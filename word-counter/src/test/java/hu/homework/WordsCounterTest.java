package hu.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordsCounterTest {

	@Test
	public void test_main() {
		
		WordsCounter.main(null);
	}
	
	
	@Test
	public void test_file1_file2() {
		WordsCounter wc = new WordsCounter();
		// load text files in parallel
		wc.load("file1.txt", "file2.txt");
		// display words statistics
		Assertions.assertEquals("\n"+
				"file 2\n" + 
				"first 1\n" + 
				"is 2\n" + 
				"one 1\n" + 
				"second 1\n" + 
				"the 2\n" + 
				"this 2\n" + 
				"** total: 11", wc.displayStatus());
	}
	
	
	
	@Test
	public void test_file2_file3_javawki() {
		WordsCounter wc = new WordsCounter();
		wc.load( "file2.txt", "file3.txt", "javawiki.txt");
		Assertions.assertEquals(788, wc.total());
	}

}
