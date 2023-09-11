package hu.homework.recognize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineTest {

	@Test
	public void test_recognizeline() {
		String[] lines ={
				
				" _        _  _  _     _  _ \n",
				"|_ |_|  | _||_ |_   ||_| _|\n",
				"|_|  |  | _||_||_|  ||_| _|\n",
				"\n"
		};
		Line line = new Line(lines);
		
		Assertions.assertEquals("641366183", line.recognize());
	}
	
	
	@Test
	public void test_recognizeline_invalid() {
		
		String[] lines ={
				
				" _        _  _        _  _ \n",
				"|_ |_|  | _||_ |_   ||_| _|\n",
				"|_|  |  | _||_||_|  ||_| _|\n",
				"\n"
		};

		Line line = new Line(lines);
		
		Assertions.assertEquals("64136?183 ILLEGAL", line.recognize());
	}
}
