package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class LongBufferTest {

	private LongBuffer buffer1;
	private LongBuffer buffer2;
	private LongBuffer buffer3;

	@BeforeEach
	void setUp() {
		buffer1 = LongBuffer.wrap(new long[]{1L, 2L, 3L});
		buffer2 = LongBuffer.allocate(3);
		buffer3 = LongBuffer.wrap(new long[]{4L, 5L, 6L});
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
	void shouldPutAnotherLongBufferIntoBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1L, buffer2.get());
		assertEquals(2L, buffer2.get());
		assertEquals(3L, buffer2.get());
	}

	@Test
	void shouldThrowExceptionWhenPuttingSameBufferIntoItself() {
		assertThrows(IllegalArgumentException.class, () -> buffer1.put(buffer1));
	}

	@Test
	void shouldThrowExceptionWhenSourceBufferIsTooLarge() {
		LongBuffer sourceBuffer = LongBuffer.wrap(new long[]{7L, 8L, 9L, 10L});
		assertThrows(BufferOverflowException.class, () -> buffer2.put(sourceBuffer));
	}

	@Test
	void shouldPutLongsFromAnotherBufferCorrectly() {
		buffer2.put(buffer1);
		buffer2.flip();
		assertEquals(1L, buffer2.get());
		assertEquals(2L, buffer2.get());
		assertEquals(3L, buffer2.get());
	}
}
