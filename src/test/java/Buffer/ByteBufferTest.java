package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class ByteBufferTest {

    private ByteBuffer bufferSize0, bufferSize100, bufferDirectAllocation;

    private enum ByteBufferSize {
        SIZE_ZERO(ByteBuffer.allocate(0)),
        SIZE_100(ByteBuffer.allocate(100)),
        DIRECT_ALLOCATION(ByteBuffer.allocateDirect(0));

        private final ByteBuffer byteBuffer;

        ByteBufferSize(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
        }
        
        public ByteBuffer getByteBuffer() {
            return byteBuffer;
        }
    }

    @BeforeEach
    void setUp() {
        bufferSize0 = ByteBufferSize.SIZE_ZERO.getByteBuffer();
        bufferSize100 = ByteBufferSize.SIZE_100.getByteBuffer();
        bufferDirectAllocation = ByteBufferSize.DIRECT_ALLOCATION.getByteBuffer();

        for (byte i = 1; i <= 100; i++) {
            bufferSize100.put(i);
        }

        bufferSize100.flip();
    }

    @AfterEach
    void tearDown() {
        bufferSize100.clear();
    }

    @Test
    void shouldVerifyAllocatedSizeIsPositive() {
        assertTrue(bufferSize0.capacity() >= 0);
        assertThrows(IllegalArgumentException.class, () -> ByteBuffer.allocate(-1));
    }

    @Test
    void shouldVerifyDirectAllocatedSizeIsPositive() {
        assertTrue(bufferDirectAllocation.capacity() >= 0);
    }

    @Test
    void shouldVerifyBufferViewAsCharBuffer() {
        assertTrue(bufferSize0.asCharBuffer() instanceof CharBuffer);
    }

    @Test
    void shouldVerifyBufferViewAsDoubleBuffer() {
        assertTrue(bufferSize0.asDoubleBuffer() instanceof DoubleBuffer);
    }

    @Test
    void shouldVerifyBufferViewAsFloatBuffer() {
        assertTrue(bufferSize0.asFloatBuffer() instanceof FloatBuffer);
    }

    @Test
    void shouldVerifyBufferViewAsIntBuffer() {
        assertTrue(bufferSize0.asIntBuffer() instanceof IntBuffer);
    }

    @Test
    void shouldVerifyBufferViewAsLongBuffer() {
        assertTrue(bufferSize0.asLongBuffer() instanceof LongBuffer);
    }

    @Test
    void shouldVerifyBufferViewAsShortBuffer() {
        assertTrue(bufferSize0.asShortBuffer() instanceof ShortBuffer);
    }

    @Test
    void shouldVerifyReadOnlyBufferCreatedFromOriginalBuffer() {
        assertEquals(bufferSize0.capacity(), bufferSize0.asReadOnlyBuffer().capacity());
        assertTrue(bufferSize0.asReadOnlyBuffer().isReadOnly());
    }

    @Test
    void shouldCompressEmptyBufferCorrectly() {
        bufferSize100.flip();
        bufferSize100.compact();
        assertEquals(0, bufferSize100.position());
        assertEquals(100, bufferSize100.limit());
    }

    @Test
    void shouldCreateDuplicateBufferCopy() {
        ByteBuffer bufferDuplicate = bufferSize100.duplicate();
        assertEquals(bufferSize100, bufferDuplicate);
    }

    @Test
    void shouldGetByteArrayFromBuffer() {
        byte[] destination = new byte[10];
        bufferSize100.get(destination);

        byte[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, destination);
    }

    @Test
    void shouldGetByteArrayWithOffsetAndLength() {
        byte[] destination = new byte[5];
        bufferSize100.get(destination, 0, 5);

        byte[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, destination);
    }

    @Test
    void shouldGetCharFromBuffer() {
        bufferSize100.putChar('A');
        bufferSize100.flip();

        char value = bufferSize100.getChar();
        assertEquals('A', value);
    }

    @Test
    void shouldGetCharAtIndexFromBuffer() {
        bufferSize100.putChar('A');
        bufferSize100.putChar('B');
        bufferSize100.flip();

        char value = bufferSize100.getChar(2);
        assertEquals('B', value);
    }

    @Test
    void shouldGetDoubleFromBuffer() {
        bufferSize100.putDouble(3.14);
        bufferSize100.flip();

        double value = bufferSize100.getDouble();
        assertEquals(3.14, value);
    }

    @Test
    void shouldGetDoubleAtIndexFromBuffer() {
        bufferSize100.putDouble(3.14);
        bufferSize100.putDouble(6.28);
        bufferSize100.flip();

        double value = bufferSize100.getDouble(8);
        assertEquals(6.28, value);
    }

    @Test
    void shouldGetFloatFromBuffer() {
        bufferSize100.putFloat(1.23f);
        bufferSize100.flip();

        float value = bufferSize100.getFloat();
        assertEquals(1.23f, value);
    }

    @Test
    void shouldGetFloatAtIndexFromBuffer() {
        bufferSize100.putFloat(1.23f);
        bufferSize100.putFloat(4.56f);
        bufferSize100.flip();

        float value = bufferSize100.getFloat(4);
        assertEquals(4.56f, value);
    }

    @Test
    void shouldGetIntFromBuffer() {
        bufferSize100.putInt(123);
        bufferSize100.flip();

        int value = bufferSize100.getInt();
        assertEquals(123, value);
    }

    @Test
    void shouldGetIntAtIndexFromBuffer() {
        bufferSize100.putInt(123);
        bufferSize100.putInt(456);
        bufferSize100.flip();

        int value = bufferSize100.getInt(4);
        assertEquals(456, value);
    }

    @Test
    void shouldGetLongFromBuffer() {
        bufferSize100.putLong(123456789L);
        bufferSize100.flip();

        long value = bufferSize100.getLong();
        assertEquals(123456789L, value);
    }

    @Test
    void shouldGetLongAtIndexFromBuffer() {
        bufferSize100.putLong(123456789L);
        bufferSize100.putLong(987654321L);
        bufferSize100.flip();

        long value = bufferSize100.getLong(8);
        assertEquals(987654321L, value);
    }

    @Test
    void shouldGetShortFromBuffer() {
        bufferSize100.putShort((short) 100);
        bufferSize100.flip();

        short value = bufferSize100.getShort();
        assertEquals(100, value);
    }

    @Test
    void shouldGetShortAtIndexFromBuffer() {
        bufferSize100.putShort((short) 100);
        bufferSize100.putShort((short) 200);
        bufferSize100.flip();

        short value = bufferSize100.getShort(2);
        assertEquals(200, value);
    }

    @Test
    void shouldCreateBufferWithSameHashCodeAsSourceBuffer() {
        Buffer bufferHashTest = bufferSize100.duplicate();
        assertEquals(bufferSize100.hashCode(), bufferHashTest.hashCode());
    }

    @Test
    void shouldWrapByteArrayWithOffsetAndLengthIntoBuffer() {
        byte[] array = {1, 2, 3, 4};

        ByteBuffer bufferWrapOffset = ByteBuffer.wrap(array, 1, 2);
        assertEquals(2, bufferWrapOffset.remaining());
        assertEquals(2, bufferWrapOffset.get());
        assertEquals(3, bufferWrapOffset.get());

        assertThrows(IndexOutOfBoundsException.class, () -> ByteBuffer.wrap(array, -1, 3));
    }

    @Test
    void shouldWrapByteArrayIntoBuffer() {
        byte[] array = {10, 20, 30, 40, 50};

        ByteBuffer bufferWrapArray = ByteBuffer.wrap(array);
        assertEquals(array.length, bufferWrapArray.remaining());

        for (byte b : array) {
            assertEquals(b, bufferWrapArray.get());
        }

        bufferWrapArray.put(0, (byte) 99);
        assertEquals(99, array[0]);
    }

    @Test
    void shouldVerifyBufferDefaultOrderIsBigEndian() {
        assertEquals(ByteOrder.BIG_ENDIAN, bufferSize100.order());
    }

    @Test
    void shouldSetBufferOrderToLittleEndian() {
        bufferSize100.order(ByteOrder.LITTLE_ENDIAN);

        assertEquals(ByteOrder.LITTLE_ENDIAN, bufferSize100.order());
    }

    @Test
    void shouldSetBufferOrderToBigEndianAfterLittleEndian() {
        bufferSize100.order(ByteOrder.LITTLE_ENDIAN);
        bufferSize100.order(ByteOrder.BIG_ENDIAN);

        assertEquals(ByteOrder.BIG_ENDIAN, bufferSize100.order());
    }

    @Test
    void shouldPutAndRetrieveByteInBuffer() {
        bufferSize100.put((byte) 100);
        bufferSize100.flip();
        assertEquals(100, bufferSize100.get());
    }

    @Test
    void shouldPutByteArrayIntoBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        byte[] source = {1, 2, 3, 4, 5};
        buffer.put(source);
        buffer.flip();
        for (int i = 0; i < source.length; i++) {
            assertEquals(source[i], buffer.get());
        }
    }

    @Test
    void shouldPutByteArrayWithOffsetAndLengthIntoBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        byte[] source = {1, 2, 3, 4, 5};
        buffer.put(source, 1, 3);
        buffer.flip();
        assertEquals(2, buffer.get());
        assertEquals(3, buffer.get());
        assertEquals(4, buffer.get());
    }

    @Test
    void shouldPutByteAtSpecificIndexInBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.position(2);
        buffer.put((byte) 100);
        buffer.flip();
        buffer.position(2);
        assertEquals(100, buffer.get());
    }

    @Test
    void shouldPutCharAtSpecificIndexInBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.position(2);
        buffer.putChar('A');
        buffer.flip();
        buffer.position(2);
        assertEquals('A', buffer.getChar());
    }

    @Test
    void shouldPutFloatAtSpecificIndexInBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.position(4);
        buffer.putFloat(1.23f);
        buffer.flip();
        buffer.position(4);
        assertEquals(1.23f, buffer.getFloat());
    }

    @Test
    void shouldPutIntAtSpecificIndexInBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.position(4);
        buffer.putInt(123);
        buffer.flip();
        buffer.position(4);
        assertEquals(123, buffer.getInt());
    }

    @Test
    void shouldPutLongAtSpecificIndexInBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.position(8);
        buffer.putLong(123456789L);
        buffer.flip();
        buffer.position(8);
        assertEquals(123456789L, buffer.getLong());
    }

    @Test
    void shouldPutShortAtSpecificIndexInBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.position(2);
        buffer.putShort((short) 100);
        buffer.flip();
        buffer.position(2);
        assertEquals(100, buffer.getShort());
    }

    @Test
    void shouldCreateSliceOfBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            buffer.put((byte) (i + 1));
        }
        buffer.flip();

        ByteBuffer slicedBuffer = buffer.slice();
        assertEquals(1, slicedBuffer.get(0));
    }
}