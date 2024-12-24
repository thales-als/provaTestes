package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class FloatBufferTest {

	private FloatBuffer sourceBuffer;
	private FloatBuffer emptyBuffer;
	private FloatBuffer destinationBuffer;

	@BeforeEach
	void setUp() {
		sourceBuffer = FloatBuffer.wrap(new float[]{1.0f, 2.0f, 3.0f});
		emptyBuffer = FloatBuffer.allocate(3);
		destinationBuffer = FloatBuffer.wrap(new float[]{4.0f, 5.0f, 6.0f});
	}

	@AfterEach
	void tearDown() {
		sourceBuffer.clear();
		emptyBuffer.clear();
		destinationBuffer.clear();
	}

	@Test
	void shouldCompareBuffersWithEqualContent() {
		emptyBuffer.put(sourceBuffer);
		assertEquals(0, sourceBuffer.compareTo(emptyBuffer));
	}

	@Test
	void shouldCompareBuffersWithDifferentContent() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertTrue(sourceBuffer.compareTo(destinationBuffer) < 0);
		assertTrue(destinationBuffer.compareTo(sourceBuffer) > 0);
	}

	@Test
	void shouldThrowExceptionWhenComparingWithNullBuffer() {
		assertThrows(NullPointerException.class, () -> sourceBuffer.compareTo(null));
	}

	@Test
	void shouldPutAnotherFloatBufferIntoBufferCorrectly() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertEquals(1.0f, emptyBuffer.get());
		assertEquals(2.0f, emptyBuffer.get());
		assertEquals(3.0f, emptyBuffer.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> sourceBuffer.put(sourceBuffer));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		sourceBuffer = FloatBuffer.wrap(new float[]{7.0f, 8.0f, 9.0f, 10.0f});
		assertThrows(BufferOverflowException.class, () -> emptyBuffer.put(sourceBuffer));
	}
}
