package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class IntBufferTest {

	private IntBuffer sourceBuffer;
	private IntBuffer emptyBuffer;
	private IntBuffer destinationBuffer;

	@BeforeEach
	void setUp() {
		sourceBuffer = IntBuffer.wrap(new int[]{1, 2, 3});
		emptyBuffer = IntBuffer.allocate(3);
		destinationBuffer = IntBuffer.wrap(new int[]{4, 5, 6});
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
	void shouldPutAnotherIntBufferIntoBufferCorrectly() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertEquals(1, emptyBuffer.get());
		assertEquals(2, emptyBuffer.get());
		assertEquals(3, emptyBuffer.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> sourceBuffer.put(sourceBuffer));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		IntBuffer sourceBuffer = IntBuffer.wrap(new int[]{7, 8, 9, 10});
		assertThrows(BufferOverflowException.class, () -> emptyBuffer.put(sourceBuffer));
	}

	@Test
	void shouldPutIntsFromAnotherBufferCorrectly() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertEquals(1, emptyBuffer.get());
		assertEquals(2, emptyBuffer.get());
		assertEquals(3, emptyBuffer.get());
	}
}
