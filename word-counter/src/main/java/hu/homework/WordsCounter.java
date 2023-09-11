package hu.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import org.apache.logging.log4j.CloseableThreadContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class WordsCounter {

//	The class should contain a single map that will hold the words and the number of items of each.
//	As can be seen in the example below, the method “load” can get a few files. Each file should be
//	handled in separate threads (in parallel).
//	The method “displayStatus” will print the map.
//	Specific guidance:
//	Please use java.util.concurrent.ConcurrentHashMap and also its method putIfAbsent().
//	Note: When splitting the text into separate words, there is no need to deal with special
//	characters such as “,.-:” etc. The class skelethon should look like this:

	private ExecutorService executor;

	private java.util.concurrent.ConcurrentHashMap<String, LongAdder> wordCount = new java.util.concurrent.ConcurrentHashMap<>();

	public static void main(String[] args) {

		WordsCounter wc = new WordsCounter();
		// load text files in parallel
		wc.load("file1.txt", "file2.txt", "file3.txt");
		// display words statistics
		wc.displayStatus();

	}

	public String displayStatus() {
		try {
			log.debug("displayStatus:" + wordCount);

//		and 1
//		file 3
//		first 1
//		is 3
//		one 1
//		second 1
//		the 3
//		third 1
//		this 3
//		** total: 17

//		long total = wordCount.values().stream().map(value -> value.intValue()).reduce(0, Integer::sum);
			AtomicLong total = new AtomicLong();

			StringBuilder sb = new StringBuilder("\n");

			wordCount.entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, LongAdder>::getKey))
					.forEach(entry -> {
						sb.append(entry.getKey());
						sb.append(" ");
						sb.append(entry.getValue());
						sb.append("\n");
						total.addAndGet(entry.getValue().longValue());
					});

			sb.append("** total: ");
			sb.append(total);

			String ret = sb.toString();
			log.info(ret);

			return ret;
		} catch (Exception e) {
			log.error("displayStatus", e);
			throw e;
		}
	}

	public void load(String... files) {
		try {
			int poolsize = 10;

			if (files.length < poolsize) {
				poolsize = files.length;
			}

			// setup
			log.info("poolsize: " + poolsize + "; files: " + Arrays.asList(files));
			executor = Executors.newFixedThreadPool(poolsize);

			CompletableFuture<?>[] futures = Arrays.stream(files).map(file -> execute(file))
					.toArray(CompletableFuture[]::new);

			CompletableFuture.allOf(futures).join();

		} catch (Exception e) {
			log.error("load", e);
			throw e;
		}

		// teardown

		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

		} catch (InterruptedException e) {
			log.error("", e);
		}

	}

	private CompletableFuture<Void> execute(String fileName) {

		return CompletableFuture.runAsync(() -> {
			try (final CloseableThreadContext.Instance ctcglobal = CloseableThreadContext.put("fileName", fileName);
					BufferedReader r = new BufferedReader(new FileReader(fileName))) {
				log.info("starting...");

				StreamTokenizer streamTokenizer = new StreamTokenizer(r);
				int currentToken = streamTokenizer.nextToken();
				while (currentToken != StreamTokenizer.TT_EOF) {

					String key;

					if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
						key = "" + streamTokenizer.nval;
					} else if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
						key = streamTokenizer.sval;
					} else {
						key = "" + (char) currentToken;
					}

					log.debug("key: " + key);

					if (key != null) {

//						wordCount.computeIfAbsent(key, k ->  new LongAdder()).increment();
						wordCount.putIfAbsent(key, new LongAdder());
						wordCount.get(key).increment();
					}

					currentToken = streamTokenizer.nextToken();
				}
			} catch (IOException e) {
				log.error("", e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				log.error("", e);
				throw new IllegalArgumentException(e);
			} finally {
				log.info("Processing " + fileName + " is READY.");
			}

		}, executor).exceptionally(ex -> {
			log.error("Received exception {}, returning empty." + ex.getMessage(), ex);
//              return Set.of();
			return null;
		});

	}

	public long total() {
		long total = wordCount.values().stream().map(value -> value.intValue()).reduce(0, Integer::sum);
		return total;
	}
}