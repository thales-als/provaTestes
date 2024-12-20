package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class IntBufferTest {
	@Test
	public void testCompareTo() {
	    IntBuffer buffer1 = IntBuffer.wrap(new int[]{1, 2, 3});
	    IntBuffer buffer2 = IntBuffer.wrap(new int[]{1, 2, 3});
	    IntBuffer buffer3 = IntBuffer.wrap(new int[]{4, 5, 6});
	    
	    assertEquals(0, buffer1.compareTo(buffer2), "Os buffers devem ser iguais");
	    assertTrue(buffer1.compareTo(buffer3) < 0, "buffer1 deve ser menor que buffer3");
	    assertTrue(buffer3.compareTo(buffer1) > 0, "buffer3 deve ser maior que buffer1");
	}

	@Test
	public void testPutIntBuffer() {
	    IntBuffer buffer1 = IntBuffer.wrap(new int[]{1, 2, 3});
	    IntBuffer buffer2 = IntBuffer.allocate(3);
	    
	    buffer2.put(buffer1);
	    buffer2.flip();
	    
	    assertEquals(1, buffer2.get(), "O primeiro valor no buffer2 deve ser 1");
	    assertEquals(2, buffer2.get(), "O segundo valor no buffer2 deve ser 2");
	    assertEquals(3, buffer2.get(), "O terceiro valor no buffer2 deve ser 3");
	}

}
