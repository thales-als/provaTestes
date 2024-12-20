package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class ByteBufferTest {
	
	private ByteBuffer buffer;
	
	@BeforeEach
	void setupBefore() {
		buffer = ByteBuffer.allocate(100);
		
		for (byte i = 1; i <= 100; i++) {
            buffer.put(i);
        }
		
		buffer.flip();
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
	void deveRetornarTrueAoVerificarSeOMetodoCriouUmaViewDoBufferComoCharBuffer() {
		assertTrue(buffer.asCharBuffer() instanceof CharBuffer);
	}
	
	@Test
	void deveRetornarTrueAoVerificarSeOMetodoCriouUmaViewDoBufferComoDoubleBuffer() {
		assertTrue(buffer.asDoubleBuffer() instanceof DoubleBuffer);
	}
	
	@Test
	void deveRetornarTrueAoVerificarSeOMetodoCriouUmaViewDoBufferComoFloatBuffer() {
		assertTrue(buffer.asFloatBuffer() instanceof FloatBuffer);
	}
	
	@Test
	void deveRetornarTrueAoVerificarSeOMetodoCriouUmaViewDoBufferComoIntBuffer() {
		assertTrue(buffer.asIntBuffer() instanceof IntBuffer);
	}
	
	@Test
	void deveRetornarTrueAoVerificarSeOMetodoCriouUmaViewDoBufferComoLongBuffer() {
		assertTrue(buffer.asLongBuffer() instanceof LongBuffer);
	}
	
	@Test
	void deveRetornarTrueAoVerificarSeOMetodoCriouUmaViewDoBufferComoShortBuffer() {
		assertTrue(buffer.asShortBuffer() instanceof ShortBuffer);
	}
	
	@Test
	void deveVerificarSeOMetodoAsReadOnlyBufferCriouNovoBufferReadOnlyComOConteudoDoBufferOriginal() {
		assertEquals(buffer.capacity(), buffer.asReadOnlyBuffer().capacity());
		assertTrue(buffer.asReadOnlyBuffer().isReadOnly());
	}
	
	@Test
    void deveComprimirCorretamenteUmBufferVazio() {
        buffer.flip();

        buffer.compact();

        assertEquals(0, buffer.position());
        assertEquals(100, buffer.limit());
    }
	
	@Test
	void deveCriarUmaCopiaDoBufferOriginalComDuplicate() {
		ByteBuffer bufferDuplicate = buffer.duplicate();
		assertEquals(buffer, bufferDuplicate);
	}
	
	@Test
    void testGetByteArray() {
        byte[] destination = new byte[10];

        buffer.get(destination);

        byte[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, destination);
    }

    @Test
    void testGetByteArrayWithOffsetAndLength() {
        byte[] destination = new byte[5];

        buffer.get(destination, 0, 5);

        byte[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, destination);
    }
    
    @Test
    void testGetChar() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putChar('A');
        buffer.flip();
        
        char value = buffer.getChar();
        
        assertEquals('A', value, "O valor retornado deve ser o mesmo que o armazenado");
    }

    @Test
    void testGetCharAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putChar('A');
        buffer.putChar('B');
        buffer.flip();
        
        char value = buffer.getChar(2);
        
        assertEquals('B', value, "O valor retornado no índice especificado deve ser correto");
    }
    
    @Test
    void testGetDouble() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putDouble(3.14);
        buffer.flip();
        
        double value = buffer.getDouble();
        
        assertEquals(3.14, value, "O valor retornado deve ser o mesmo que o armazenado");
    }

    @Test
    void testGetDoubleAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putDouble(3.14);
        buffer.putDouble(6.28);
        buffer.flip();
        
        double value = buffer.getDouble(8);
        
        assertEquals(6.28, value, "O valor retornado no índice especificado deve ser correto");
    }
    
    @Test
    void testGetFloat() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putFloat(1.23f);
        buffer.flip();
        
        float value = buffer.getFloat();
        
        assertEquals(1.23f, value, "O valor retornado deve ser o mesmo que o armazenado");
    }

    @Test
    void testGetFloatAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putFloat(1.23f);
        buffer.putFloat(4.56f);
        buffer.flip();
        
        float value = buffer.getFloat(4);
        
        assertEquals(4.56f, value, "O valor retornado no índice especificado deve ser correto");
    }
    
    @Test
    void testGetInt() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(123);
        buffer.flip();
        
        int value = buffer.getInt();
        
        assertEquals(123, value, "O valor retornado deve ser o mesmo que o armazenado");
    }

    @Test
    void testGetIntAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(123);
        buffer.putInt(456);
        buffer.flip();
        
        int value = buffer.getInt(4);
        
        assertEquals(456, value, "O valor retornado no índice especificado deve ser correto");
    }
    
    @Test
    void testGetLong() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(123456789L);
        buffer.flip();
        
        long value = buffer.getLong();
        
        assertEquals(123456789L, value, "O valor retornado deve ser o mesmo que o armazenado");
    }

    @Test
    void testGetLongAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(123456789L);
        buffer.putLong(987654321L);
        buffer.flip();
        
        long value = buffer.getLong(8);
        
        assertEquals(987654321L, value, "O valor retornado no índice especificado deve ser correto");
    }
    
    @Test
    void testGetShort() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort((short) 100);
        buffer.flip();
        
        short value = buffer.getShort();
        
        assertEquals(100, value, "O valor retornado deve ser o mesmo que o armazenado");
    }

    @Test
    void testGetShortAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort((short) 100);
        buffer.putShort((short) 200);
        buffer.flip();
        
        short value = buffer.getShort(2);
        
        assertEquals(200, value, "O valor retornado no índice especificado deve ser correto");
    }
    
    @Test
    void deveCriarUmBufferComOHashCodeIgualAoDoBufferDeOrigem() {
    	Buffer testeHash = buffer.duplicate();
    	assertEquals(buffer.hashCode(), testeHash.hashCode());
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
	
	@Test
    void testOrder() {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        // Verificando a ordem de bytes padrão (BIG_ENDIAN)
        assertEquals(ByteOrder.BIG_ENDIAN, buffer.order(), "A ordem de bytes deve ser BIG_ENDIAN por padrão");
    }
	
	@Test
    void testOrderWithArgument() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        
        // Definindo a ordem de bytes como LITTLE_ENDIAN
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        
        assertEquals(ByteOrder.LITTLE_ENDIAN, buffer.order(), "A ordem de bytes deve ser LITTLE_ENDIAN após a modificação");
    }

    @Test
    void testOrderWithDefault() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        
        // Alterando para LITTLE_ENDIAN e depois retornando para o padrão (BIG_ENDIAN)
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.order(ByteOrder.BIG_ENDIAN);
        
        assertEquals(ByteOrder.BIG_ENDIAN, buffer.order(), "A ordem de bytes deve ser BIG_ENDIAN após voltar para o padrão");
    }
    
    @Test
    void testPutByte() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put((byte) 100);
        buffer.flip();
        assertEquals(100, buffer.get(), "O valor do byte colocado no buffer deve ser 100");
    }

    @Test
    void testPutByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        byte[] source = {1, 2, 3, 4, 5};
        buffer.put(source);
        buffer.flip();
        for (int i = 0; i < source.length; i++) {
            assertEquals(source[i], buffer.get(), "O byte no índice " + i + " deve ser igual ao original");
        }
    }

    @Test
    void testPutByteArrayWithOffsetAndLength() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        byte[] source = {1, 2, 3, 4, 5};
        buffer.put(source, 1, 3);
        buffer.flip();
        assertEquals(2, buffer.get(), "O primeiro valor colocado no buffer deve ser 2");
        assertEquals(3, buffer.get(), "O segundo valor colocado no buffer deve ser 3");
        assertEquals(4, buffer.get(), "O terceiro valor colocado no buffer deve ser 4");
    }

    @Test
    void testPutByteAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.position(2);  // Ajustar posição para o índice 2
        buffer.put((byte) 100);
        buffer.flip();
        buffer.position(2);  // Verifique no índice 2
        assertEquals(100, buffer.get(), "O valor do byte na posição 2 deve ser 100");
    }

    @Test
    void testPutCharAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.position(2);  // Ajustar posição para o índice 2
        buffer.putChar('A');
        buffer.flip();
        buffer.position(2);  // Verifique no índice 2
        assertEquals('A', buffer.getChar(), "O valor char na posição 2 deve ser 'A'");
    }

    @Test
    void testPutFloatAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.position(4);  // Ajustar posição para o índice 4
        buffer.putFloat(1.23f);
        buffer.flip();
        buffer.position(4);  // Verifique no índice 4
        assertEquals(1.23f, buffer.getFloat(), "O valor float na posição 4 deve ser 1.23");
    }

    @Test
    void testPutIntAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.position(4);  // Ajustar posição para o índice 4
        buffer.putInt(123);
        buffer.flip();
        buffer.position(4);  // Verifique no índice 4
        assertEquals(123, buffer.getInt(), "O valor int na posição 4 deve ser 123");
    }

    @Test
    void testPutLongAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.position(8);  // Ajustar posição para o índice 8
        buffer.putLong(123456789L);
        buffer.flip();
        buffer.position(8);  // Verifique no índice 8
        assertEquals(123456789L, buffer.getLong(), "O valor long na posição 8 deve ser 123456789L");
    }

    @Test
    void testPutShortAtIndex() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.position(2);  // Ajustar posição para o índice 2
        buffer.putShort((short) 100);
        buffer.flip();
        buffer.position(2);  // Verifique no índice 2
        assertEquals(100, buffer.getShort(), "O valor short na posição 2 deve ser 100");
    }
    
    @Test
    void testSlice() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            buffer.put((byte) (i + 1));
        }
        buffer.flip();
        
        ByteBuffer slicedBuffer = buffer.slice();
        assertEquals(1, slicedBuffer.get(0), "O primeiro valor no slice deve ser 1");
    }
}