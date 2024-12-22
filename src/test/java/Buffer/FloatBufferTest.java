package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class FloatBufferTest {

	private FloatBuffer buffer1;
	private FloatBuffer buffer2;
	private FloatBuffer buffer3;

	@BeforeEach
	void setUp() {
		buffer1 = FloatBuffer.wrap(new float[]{1.0f, 2.0f, 3.0f});
		buffer2 = FloatBuffer.allocate(3);
		buffer3 = FloatBuffer.wrap(new float[]{4.0f, 5.0f, 6.0f});
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
	void shouldPutAnotherFloatBufferIntoBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1.0f, buffer2.get());
		assertEquals(2.0f, buffer2.get());
		assertEquals(3.0f, buffer2.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> buffer1.put(buffer1));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		FloatBuffer sourceBuffer = FloatBuffer.wrap(new float[]{7.0f, 8.0f, 9.0f, 10.0f});
		assertThrows(BufferOverflowException.class, () -> buffer2.put(sourceBuffer));
	}

	@Test
	void shouldPutFloatsFromAnotherBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1.0f, buffer2.get());
		assertEquals(2.0f, buffer2.get());
		assertEquals(3.0f, buffer2.get());
	}
}
