package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class LongBufferTest {

	private LongBuffer sourceBuffer;
	private LongBuffer emptyBuffer;
	private LongBuffer destinationBuffer;

	@BeforeEach
	void setUp() {
		sourceBuffer = LongBuffer.wrap(new long[]{1L, 2L, 3L});
		emptyBuffer = LongBuffer.allocate(3);
		destinationBuffer = LongBuffer.wrap(new long[]{4L, 5L, 6L});
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
	void shouldPutAnotherLongBufferIntoBufferCorrectly() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertEquals(1L, emptyBuffer.get());
		assertEquals(2L, emptyBuffer.get());
		assertEquals(3L, emptyBuffer.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> sourceBuffer.put(sourceBuffer));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		sourceBuffer = LongBuffer.wrap(new long[]{7L, 8L, 9L, 10L});
		assertThrows(BufferOverflowException.class, () -> emptyBuffer.put(sourceBuffer));
	}
}
