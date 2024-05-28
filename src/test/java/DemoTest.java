import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;



public class DemoTest {
	
	@Test
	public void testValidTriangle() {
        assertTrue(Demo.isTriangle(3, 4, 5)); 
        assertTrue(Demo.isTriangle(5, 12, 13)); 
        assertTrue(Demo.isTriangle(7, 8, 9)); 
    }
	
	@Test
	public void testInvalidTriangleZero() {
        assertFalse(Demo.isTriangle(1, 1, 0)); 
        assertFalse(Demo.isTriangle(0, 1, 1)); 
        assertFalse(Demo.isTriangle(7, 0, 9)); 
    }
	
	@Test
	public void testInvalidTriangleNegativeDigit() {
        assertFalse(Demo.isTriangle(-1, 2, 3)); 
        assertFalse(Demo.isTriangle(-1, 1, 1));
        assertFalse(Demo.isTriangle(2, -1, 3));
    }
	
	@Test
	public void testInvalidTriangleNegativeDigits() {
        assertFalse(Demo.isTriangle(-1, -2, -3)); 
        assertFalse(Demo.isTriangle(-1, -1, -1)); 
        assertFalse(Demo.isTriangle(-2, -1, -3)); 
    }
	
	@Test
	public void testvalidTriangleDoubles() {
        assertTrue(Demo.isTriangle(0.5, 0.5, 0.9));
        assertTrue(Demo.isTriangle(0.5, 0.3, 0.4)); 
        assertTrue(Demo.isTriangle(19.3, 40, 22.2)); 
    }
	
	//
    @Test
    public void testMainValidTriangle() {
        String input = "3\n4\n5\n";  // Inputs for side_1, side_2, side_3
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the output to verify it
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the main method
        Demo.main(new String[0]);

        // Expected output
        // Expected output
        String expectedOutput = "Enter side 1: " + System.getProperty("line.separator"); 
        expectedOutput += "Enter side 2: " + System.getProperty("line.separator"); 
        expectedOutput += "Enter side 3: " + System.getProperty("line.separator"); 
        expectedOutput += "This is a triangle." +  System.getProperty("line.separator");

        // Assert the output
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainInvalidTriangle() {
        String input = "1\n1\n3\n";  // Inputs for side_1, side_2, side_3
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the output to verify it
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Call the main method
        Demo.main(new String[0]);

        // Expected output
        String expectedOutput = "Enter side 1: " + System.getProperty("line.separator"); 
        expectedOutput += "Enter side 2: " + System.getProperty("line.separator"); 
        expectedOutput += "Enter side 3: " + System.getProperty("line.separator"); 
        expectedOutput += "This is not a triangle." +  System.getProperty("line.separator");
        
        
        // Assert the output
        assertEquals(expectedOutput, outContent.toString());
    }
	
	

}
