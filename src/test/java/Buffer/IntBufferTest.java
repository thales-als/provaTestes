package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class IntBufferTest {

	private IntBuffer buffer1;
	private IntBuffer buffer2;
	private IntBuffer buffer3;

	@BeforeEach
	void setUp() {
		buffer1 = IntBuffer.wrap(new int[]{1, 2, 3});
		buffer2 = IntBuffer.allocate(3);
		buffer3 = IntBuffer.wrap(new int[]{4, 5, 6});
	}

	@AfterEach
	void tearDown() {
		buffer1.clear();
		buffer2.clear();
		buffer3.clear();
	}

	@Test
	void shouldCompareBuffersWithEqualContent() {
		buffer2.put(buffer1);
		assertEquals(0, buffer1.compareTo(buffer2));
	}

	@Test
	void shouldCompareBuffersWithDifferentContent() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertTrue(buffer1.compareTo(buffer3) < 0);
		assertTrue(buffer3.compareTo(buffer1) > 0);
	}

	@Test
	void shouldThrowExceptionWhenComparingWithNullBuffer() {
		assertThrows(NullPointerException.class, () -> buffer1.compareTo(null));
	}

	@Test
	void shouldPutAnotherIntBufferIntoBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1, buffer2.get());
		assertEquals(2, buffer2.get());
		assertEquals(3, buffer2.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> buffer1.put(buffer1));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		IntBuffer sourceBuffer = IntBuffer.wrap(new int[]{7, 8, 9, 10});
		assertThrows(BufferOverflowException.class, () -> buffer2.put(sourceBuffer));
	}

	@Test
	void shouldPutIntsFromAnotherBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1, buffer2.get());
		assertEquals(2, buffer2.get());
		assertEquals(3, buffer2.get());
	}
}
