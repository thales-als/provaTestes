package Buffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MappedByteBufferTest {
	
	private static final String TEST_FILE = "testfile.dat";
    private MappedByteBuffer mappedByteBuffer;
    private FileChannel fileChannel;

    @BeforeEach
    void setUp() throws IOException {
        File file = new File(TEST_FILE);
        if (!file.exists()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(new byte[1024]);
            }
        }
        
        fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
    }

    @AfterEach
    void tearDown() throws IOException {
    	fileChannel.close();
        new File(TEST_FILE).delete();
    }

    @Test
    void testForce() {
        mappedByteBuffer.put(0, (byte) 1);
        mappedByteBuffer.force();

        assertEquals(1, mappedByteBuffer.get(0));
    }

    @Test
    void testIsLoaded() {
        boolean isLoaded = mappedByteBuffer.isLoaded();
        assertNotNull(isLoaded);
    }


    @Test
    void testLoad() {
        MappedByteBuffer loadedBuffer = mappedByteBuffer.load();

        assertSame(mappedByteBuffer, loadedBuffer);
        assertTrue(mappedByteBuffer.isLoaded());
    }
}