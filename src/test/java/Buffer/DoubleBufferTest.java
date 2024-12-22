package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class DoubleBufferTest {

	private DoubleBuffer buffer1;
	private DoubleBuffer buffer2;
	private DoubleBuffer buffer3;

	@BeforeEach
	void setUp() {
		buffer1 = DoubleBuffer.wrap(new double[]{1.0, 2.0, 3.0});
		buffer2 = DoubleBuffer.allocate(3);
		buffer3 = DoubleBuffer.wrap(new double[]{4.0, 5.0, 6.0});
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
	void shouldPutAnotherDoubleBufferIntoBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1.0, buffer2.get());
		assertEquals(2.0, buffer2.get());
		assertEquals(3.0, buffer2.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> buffer1.put(buffer1));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		DoubleBuffer sourceBuffer = DoubleBuffer.wrap(new double[]{7.0, 8.0, 9.0, 10.0});
		assertThrows(BufferOverflowException.class, () -> buffer2.put(sourceBuffer));
	}

	@Test
	void shouldPutDoublesFromAnotherBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1.0, buffer2.get());
		assertEquals(2.0, buffer2.get());
		assertEquals(3.0, buffer2.get());
	}
}
