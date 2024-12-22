package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class DoubleBufferTest {

	private DoubleBuffer sourceBuffer;
	private DoubleBuffer emptyBuffer;
	private DoubleBuffer destinationBuffer;

	@BeforeEach
	void setUp() {
		sourceBuffer = DoubleBuffer.wrap(new double[]{1.0, 2.0, 3.0});
		emptyBuffer = DoubleBuffer.allocate(3);
		destinationBuffer = DoubleBuffer.wrap(new double[]{4.0, 5.0, 6.0});
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
	void shouldPutAnotherDoubleBufferIntoBufferCorrectly() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertEquals(1.0, emptyBuffer.get());
		assertEquals(2.0, emptyBuffer.get());
		assertEquals(3.0, emptyBuffer.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> sourceBuffer.put(sourceBuffer));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		DoubleBuffer sourceBuffer = DoubleBuffer.wrap(new double[]{7.0, 8.0, 9.0, 10.0});
		assertThrows(BufferOverflowException.class, () -> emptyBuffer.put(sourceBuffer));
	}

	@Test
	void shouldPutDoublesFromAnotherBufferCorrectly() {
		emptyBuffer.put(sourceBuffer);
		emptyBuffer.flip();
		assertEquals(1.0, emptyBuffer.get());
		assertEquals(2.0, emptyBuffer.get());
		assertEquals(3.0, emptyBuffer.get());
	}
}
