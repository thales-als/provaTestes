package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class BufferTest {

    private Buffer bufferSize0, bufferSize10, bufferDirectAllocation;

    private enum BufferSize {
        SIZE_ZERO(ByteBuffer.allocate(0)),
        SIZE_TEN(ByteBuffer.allocate(10)),
        DIRECT_ALLOCATION(ByteBuffer.allocateDirect(0));

        private final Buffer buffer;

        BufferSize(Buffer buffer) {
            this.buffer = buffer;
        }

        public Buffer getBuffer() {
            return buffer;
        }
    }

    @BeforeEach
    void setupBeforeEachTest() {
        bufferSize0 = BufferSize.SIZE_ZERO.getBuffer();
        bufferSize10 = BufferSize.SIZE_TEN.getBuffer();
        bufferDirectAllocation = BufferSize.DIRECT_ALLOCATION.getBuffer();
    }

    @AfterEach
    void cleanupAfterEachTest() {
        bufferSize0.clear();
        bufferSize10.clear();
    }

    @Test
    void shouldReturnNonNullArrayWhenArrayMethodIsCalled() {
        assertNotNull(bufferSize0.array());
    }

    @Test
    void shouldReturnZeroOffsetWhenArrayOffsetMethodIsCalled() {
        assertEquals(0, bufferSize0.arrayOffset());
    }

    @Test
    void shouldReturnZeroCapacityWhenCapacityMethodIsCalled() {
        assertEquals(0, bufferSize0.capacity());
    }

    @Test
    void shouldReturnDefaultValuesAfterClearMethodExecution() {
        bufferSize0.clear();
        assertEquals(0, bufferSize0.capacity());
        assertEquals(bufferSize0.capacity(), bufferSize0.limit());
    }

    @Test
    void shouldChangePositionAndLimitAfterFlipMethodExecution() {
        bufferSize0.flip();
        assertEquals(bufferSize0.position(), bufferSize0.limit());
        assertEquals(0, bufferSize0.position());
    }

    @Test
    void shouldConfirmBufferHasArrayWhenHasArrayMethodIsCalled() {
        assertTrue(bufferSize0.hasArray());
    }

    @Test
    void shouldReturnTrueWhenHasRemainingConfirmsAvailableSpaces() {
        boolean condition = bufferSize10.position() < bufferSize10.limit();
        assertEquals(bufferSize10.hasRemaining(), condition);
    }

    @Test
    void shouldConfirmBufferIsDirectlyAllocated() {
        assertTrue(bufferDirectAllocation.isDirect());
    }

    @Test
    void shouldReturnFalseWhenIsReadOnlyMethodIsCalledOnNonReadOnlyBuffer() {
        assertFalse(bufferSize0.isReadOnly());
    }

    @Test
    void shouldReturnZeroWhenLimitMethodIsCalledOnZeroSizeBuffer() {
        assertEquals(0, bufferSize0.limit());
    }

    @Test
    void shouldChangeBufferLimitWhenLimitMethodWithNewLimitIsCalled() {
        int newLimit = 5;
        bufferSize10.limit(newLimit);
        assertEquals(newLimit, bufferSize10.limit());
        assertThrows(IllegalArgumentException.class, () -> bufferSize10.limit(-1));
        assertThrows(IllegalArgumentException.class, () -> bufferSize10.limit(bufferSize10.capacity() + 1));
    }

    @Test
    void shouldMaintainLastMarkedPositionAfterReset() {
        bufferSize10.position(5).mark();
        assertEquals(bufferSize10.reset().position(), bufferSize10.position());

        bufferSize10.position(2).mark();
        assertEquals(bufferSize10.reset().position(), bufferSize10.position());
    }

    @Test
    void shouldReturnZeroWhenPositionMethodIsCalledOnZeroSizeBuffer() {
        assertEquals(0, bufferSize0.position());
    }

    @Test
    void shouldChangeBufferPositionWhenPositionMethodWithNewPositionIsCalled() {
        int newPosition = 5;
        bufferSize10.position(newPosition);
        assertEquals(newPosition, bufferSize10.position());
    }

    @Test
    void shouldReturnPositiveRemainingWhenRemainingMethodIsCalled() {
        assertTrue(bufferSize0.remaining() >= 0);
    }

    @Test
    void shouldRestorePositionToMarkedPositionWhenResetMethodIsCalled() {
        bufferSize10.position(5).mark();
        bufferSize10.position(8);
        bufferSize10.reset();

        assertEquals(5, bufferSize10.position());
    }

    @Test
    void shouldResetPositionToZeroWhenRewindMethodIsCalled() {
        bufferSize10.position(5).mark();
        bufferSize10.rewind();
        assertEquals(0, bufferSize10.position());
    }
}
