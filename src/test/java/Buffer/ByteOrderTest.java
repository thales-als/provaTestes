package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class ByteOrderTest {
	        
	@Test
    void deveVerificarSeORetornoDoMetodoNativeOrderNaoEhNulo() {
        assertNotNull(ByteOrder.nativeOrder());
    }

    @Test
    void deveVerificarSeORetornoDoMetodoNativeOrderEhUmEnumValido() {
        ByteOrder order = ByteOrder.nativeOrder();
        assertTrue(order == ByteOrder.BIG_ENDIAN || order == ByteOrder.LITTLE_ENDIAN);
    }
}