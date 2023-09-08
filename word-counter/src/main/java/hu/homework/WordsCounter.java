package hu.homework;

public class WordsCounter {

//	The class should contain a single map that will hold the words and the number of items of each.
//	As can be seen in the example below, the method “load” can get a few files. Each file should be
//	handled in separate threads (in parallel).
//	The method “displayStatus” will print the map.
//	Specific guidance:
//	Please use java.util.concurrent.ConcurrentHashMap and also its method putIfAbsent().
//	Note: When splitting the text into separate words, there is no need to deal with special
//	characters such as “,.-:” etc. The class skelethon should look like this:

	public static void main(String[] args) {
		WordsCounter wc = new WordsCounter();
	// load text files in parallel
		wc.load("file1.txt", "file2.txt", "file3.txt");
	// display words statistics
		wc.displayStatus();
	}

	private void displayStatus() {
		// TODO Auto-generated method stub

	}

	private void load(String... files) {
		// TODO Auto-generated method stub

	}
}