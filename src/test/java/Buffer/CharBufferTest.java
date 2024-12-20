package Buffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharBufferTest {
    
    private CharBuffer buffer;
    private CharSequence csq;

    enum TestCharSequences {
        HELLO_WORLD("HelloWorld"),
        HELLO("Hello"),
        WORLD("World");

        private final String value;

        TestCharSequences(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @BeforeEach
    void setUp() {
        buffer = CharBuffer.allocate(50);
        csq = TestCharSequences.HELLO_WORLD.getValue();
    }

    @AfterEach
    void tearDown() {
        buffer.clear();
    }

    @Test
    void shouldAppendSpecifiedCharToBuffer() {
        buffer.append('A');
        assertNotNull(buffer);
    }

    @Test
    void shouldAppendSpecifiedCharSequenceToBuffer() {
        buffer.append(csq).flip();
        assertNotNull(buffer);
        assertEquals(TestCharSequences.HELLO_WORLD.getValue(), buffer.toString());
    }

    @Test
    void shouldAppendSpecifiedCharSequenceWithinRange() {
        buffer.append(csq, 0, 5);
        buffer.append(csq, 5, 10);
        buffer.flip();

        assertEquals(TestCharSequences.HELLO_WORLD.getValue(), buffer.toString());
    }

    @Test
    void shouldReturnCorrectValueFromCharAtSpecificIndex() {
        buffer.append(csq).flip();

        assertEquals('H', buffer.charAt(0));
        assertEquals('W', buffer.charAt(5));
        assertEquals('r', buffer.charAt(7));
    }

    @Test
    void shouldReturnStreamOfCharValuesFromBuffer() {
        buffer.append(csq);
        IntStream charStream = buffer.chars();

        assertNotNull(charStream);
    }

    @Test
    void shouldPutCharBufferIntoAnotherBuffer() {
        CharBuffer source = CharBuffer.wrap(TestCharSequences.HELLO.getValue());
        buffer.put(source);
        buffer.flip();
        assertEquals('H', buffer.get());
    }

    @Test
    void shouldPutStringIntoBuffer() {
        String str = TestCharSequences.HELLO.getValue();
        buffer.put(str);
        buffer.flip();
        assertEquals('H', buffer.get());
    }

    @Test
    void shouldPutSubstringIntoBufferWithOffset() {
        String str = "Hello, World!";
        buffer.put(str, 7, 12);
        buffer.flip();
        assertEquals('W', buffer.get());
    }

    @Test
    void shouldReturnCorrectLengthOfBuffer() {
        buffer.put(TestCharSequences.HELLO.getValue());
        buffer.flip();
        assertEquals(5, buffer.length());
    }

    @Test
    void shouldReadSourceBufferIntoDestinationBuffer() throws IOException {
        buffer = CharBuffer.wrap(csq);
        CharBuffer destinationBuffer = CharBuffer.allocate(50);
        int bytesRead = buffer.read(destinationBuffer);
        destinationBuffer.flip();

        assertEquals(10, bytesRead);
    }

    @Test
    void shouldReturnSubsequenceFromBuffer() {
        buffer = CharBuffer.wrap(csq);
        CharBuffer subBuffer = buffer.subSequence(0, 5);

        assertEquals(TestCharSequences.HELLO.getValue(), subBuffer.toString());
    }

    @Test
    void shouldWrapCharSequenceIntoBuffer() {
        buffer = CharBuffer.wrap(csq);
        assertNotNull(buffer);
        assertEquals(TestCharSequences.HELLO_WORLD.getValue(), buffer.toString());
    }

    @Test
    void shouldWrapCharSequenceWithinRangeIntoBuffer() {
        buffer = CharBuffer.wrap(csq, 5, 10);
        assertNotNull(buffer);
        assertEquals(TestCharSequences.WORLD.getValue(), buffer.toString());
    }
}
