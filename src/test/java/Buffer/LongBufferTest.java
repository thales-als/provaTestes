package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class LongBufferTest {
	@Test
	public void testCompareTo() {
	    LongBuffer buffer1 = LongBuffer.wrap(new long[]{1L, 2L, 3L});
	    LongBuffer buffer2 = LongBuffer.wrap(new long[]{1L, 2L, 3L});
	    LongBuffer buffer3 = LongBuffer.wrap(new long[]{4L, 5L, 6L});
	    
	    assertEquals(0, buffer1.compareTo(buffer2), "Os buffers devem ser iguais");
	    assertTrue(buffer1.compareTo(buffer3) < 0, "buffer1 deve ser menor que buffer3");
	    assertTrue(buffer3.compareTo(buffer1) > 0, "buffer3 deve ser maior que buffer1");
	}

	@Test
	public void testPutLongBuffer() {
	    LongBuffer buffer1 = LongBuffer.wrap(new long[]{1L, 2L, 3L});
	    LongBuffer buffer2 = LongBuffer.allocate(3);
	    
	    buffer2.put(buffer1);
	    buffer2.flip();
	    
	    assertEquals(1L, buffer2.get(), "O primeiro valor no buffer2 deve ser 1L");
	    assertEquals(2L, buffer2.get(), "O segundo valor no buffer2 deve ser 2L");
	    assertEquals(3L, buffer2.get(), "O terceiro valor no buffer2 deve ser 3L");
	}

}
