package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class DoubleBufferTest {
	@Test
	public void testCompareTo() {
	    DoubleBuffer buffer1 = DoubleBuffer.wrap(new double[]{1.0, 2.0, 3.0});
	    DoubleBuffer buffer2 = DoubleBuffer.wrap(new double[]{1.0, 2.0, 3.0});
	    DoubleBuffer buffer3 = DoubleBuffer.wrap(new double[]{4.0, 5.0, 6.0});
	    
	    assertEquals(0, buffer1.compareTo(buffer2), "Os buffers devem ser iguais");
	    assertTrue(buffer1.compareTo(buffer3) < 0, "buffer1 deve ser menor que buffer3");
	    assertTrue(buffer3.compareTo(buffer1) > 0, "buffer3 deve ser maior que buffer1");
	}

	@Test
	public void testPutDoubleBuffer() {
	    DoubleBuffer buffer1 = DoubleBuffer.wrap(new double[]{1.0, 2.0, 3.0});
	    DoubleBuffer buffer2 = DoubleBuffer.allocate(3);
	    
	    buffer2.put(buffer1);
	    buffer2.flip();
	    
	    assertEquals(1.0, buffer2.get(), "O primeiro valor no buffer2 deve ser 1.0");
	    assertEquals(2.0, buffer2.get(), "O segundo valor no buffer2 deve ser 2.0");
	    assertEquals(3.0, buffer2.get(), "O terceiro valor no buffer2 deve ser 3.0");
	}

}
