package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class ShortBufferTest {

	private ShortBuffer sourceBuffer;
	private ShortBuffer emptyBuffer;
	private ShortBuffer destinationBuffer;

	@BeforeEach
	void setUp() {
		sourceBuffer = ShortBuffer.wrap(new short[]{1, 2, 3});
		emptyBuffer = ShortBuffer.allocate(3);
		destinationBuffer = ShortBuffer.wrap(new short[]{4, 5, 6});
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
	void shouldPutAnotherBufferIntoBufferCorrectly() {
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
		sourceBuffer = ShortBuffer.wrap(new short[]{7, 8, 9, 10});
		assertThrows(BufferOverflowException.class, () -> emptyBuffer.put(sourceBuffer));
	}
}
