package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class ShortBufferTest {

	private ShortBuffer buffer1;
	private ShortBuffer buffer2;
	private ShortBuffer buffer3;

	@BeforeEach
	void setUp() {
		buffer1 = ShortBuffer.wrap(new short[]{1, 2, 3});
		buffer2 = ShortBuffer.allocate(3);
		buffer3 = ShortBuffer.wrap(new short[]{4, 5, 6});
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
	void shouldPutAnotherBufferIntoBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();

		// Test if elements from buffer1 are correctly placed in buffer2
		assertEquals(1, buffer2.get());
		assertEquals(2, buffer2.get());
		assertEquals(3, buffer2.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		// Test if putting the same buffer into itself throws the appropriate exception
		assertThrows(IllegalArgumentException.class, () -> buffer1.put(buffer1));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		ShortBuffer sourceBuffer = ShortBuffer.wrap(new short[]{7, 8, 9, 10});
		assertThrows(BufferOverflowException.class, () -> buffer2.put(sourceBuffer));
	}

	@Test
	void shouldPutShortsFromAnotherBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();

		assertEquals(1, buffer2.get());
		assertEquals(2, buffer2.get());
		assertEquals(3, buffer2.get());
	}
}
