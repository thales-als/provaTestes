package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class FloatBufferTest {

	@Test
	public void testCompareTo() {
	    FloatBuffer buffer1 = FloatBuffer.wrap(new float[]{1.0f, 2.0f, 3.0f});
	    FloatBuffer buffer2 = FloatBuffer.wrap(new float[]{1.0f, 2.0f, 3.0f});
	    FloatBuffer buffer3 = FloatBuffer.wrap(new float[]{4.0f, 5.0f, 6.0f});
	    
	    assertEquals(0, buffer1.compareTo(buffer2), "Os buffers devem ser iguais");
	    assertTrue(buffer1.compareTo(buffer3) < 0, "buffer1 deve ser menor que buffer3");
	    assertTrue(buffer3.compareTo(buffer1) > 0, "buffer3 deve ser maior que buffer1");
	}

	@Test
	public void testPutFloatBuffer() {
	    FloatBuffer buffer1 = FloatBuffer.wrap(new float[]{1.0f, 2.0f, 3.0f});
	    FloatBuffer buffer2 = FloatBuffer.allocate(3);
	    
	    buffer2.put(buffer1);
	    buffer2.flip();
	    
	    assertEquals(1.0f, buffer2.get(), "O primeiro valor no buffer2 deve ser 1.0f");
	    assertEquals(2.0f, buffer2.get(), "O segundo valor no buffer2 deve ser 2.0f");
	    assertEquals(3.0f, buffer2.get(), "O terceiro valor no buffer2 deve ser 3.0f");
	}	
}
