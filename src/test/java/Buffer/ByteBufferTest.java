package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class ByteBufferTest {
	
	private ByteBuffer buffer;
	
	@BeforeEach
	void setupBefore() {
		buffer = ByteBuffer.allocate(100);
	}
	
	@AfterEach
	void setupAfter() {
		buffer.clear();
	}
	
	@Test
	void deveVerificarSeOTamanhoAlocadoEhPositivo() {
		ByteBuffer testeAllocate = ByteBuffer.allocate(0);
		assertTrue(testeAllocate.capacity() >= 0);
		assertThrows(IllegalArgumentException.class, () -> ByteBuffer.allocate(-1));
	}
	
	@Test
	void deveVerificarSeOTamanhoAlocadoDiretamenteEhPositivo() {
		ByteBuffer testeAllocateDirect = ByteBuffer.allocateDirect(0);
		assertTrue(testeAllocateDirect.capacity() >= 0);
	}
	
	@Test
	void deveVerificarSeOMetodoAsReadOnlyBufferCriouNovoBufferReadOnlyComOConteudoDoBufferOriginal() {
		assertEquals(buffer.capacity(), buffer.asReadOnlyBuffer().capacity());
		assertTrue(buffer.asReadOnlyBuffer().isReadOnly());
	}
	
	@Test
    void testCompactWithEmptyBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.flip();

        buffer.compact();

        assertEquals(0, buffer.position());
        assertEquals(10, buffer.limit());
    }
	
	@Test
	void deveCriarUmaCopiaDoBufferOriginalComDuplicate() {
		ByteBuffer bufferDuplicate = buffer.duplicate();
		assertEquals(buffer, bufferDuplicate);
	}
	
	@Test
	void deveRetornarArrayInseridoEmBuffer() {
		byte[] array = {1, 2, 3, 4};
		ByteBuffer testeGetDst = ByteBuffer.wrap(array);
		
		for (byte a : array) {
			assertEquals(a, testeGetDst.get());
		}
	}
	
	@Test
    void deveInserirUmArrayDentroDeUmBufferAPartirDeOffsetETamanho() {
        byte[] array = {1, 2, 3, 4};

        ByteBuffer testeWrapOffset = ByteBuffer.wrap(array, 1, 2);
        assertEquals(2, testeWrapOffset.remaining());
        assertEquals(2, testeWrapOffset.get());
        assertEquals(3, testeWrapOffset.get());

        assertThrows(IndexOutOfBoundsException.class, () -> ByteBuffer.wrap(array, -1, 3));
    }
	
	@Test
    void deveInserirArrayDentroDeBuffer() {
        byte[] array = {10, 20, 30, 40, 50};

        ByteBuffer testeWrapArray = ByteBuffer.wrap(array);
        assertEquals(array.length, testeWrapArray.remaining());
        
        for (byte b : array) {
            assertEquals(b, testeWrapArray.get());
        }

        testeWrapArray.put(0, (byte) 99);
        assertEquals(99, array[0]);
    }
}