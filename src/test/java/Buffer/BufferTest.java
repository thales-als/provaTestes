package Buffer;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.*;
import org.junit.jupiter.api.*;

class BufferTest {
    
    private Buffer bufferTamanho0, bufferTamanho10, bufferAlocacaoDireta;
    
    @BeforeEach
    void setupBefore() {
        bufferTamanho0 = ByteBuffer.allocate(0);
        bufferTamanho10 = ByteBuffer.allocate(10);
        bufferAlocacaoDireta = ByteBuffer.allocateDirect(0);
    }
    
    @AfterEach
    void setupAfter() {
        bufferTamanho0.clear();
        bufferTamanho10.clear();
    }
    
    @Test
    void deveRetornarArrayNaoNuloQuandoMetodoArrayForChamado() {
        assertNotNull(bufferTamanho0.array());
    }
    
    @Test
    void deveRetornarOffsetZeroQuandoMetodoArrayOffsetForChamado() {
        assertEquals(0, bufferTamanho0.arrayOffset());
    }
    
    @Test
    void deveRetornarZeroDeCapacidadeQuandoMetodoCapacityForChamado() {
        assertEquals(0, bufferTamanho0.capacity());
    }
    
    @Test
    void deveRetornarValoresPadraoAposExecucaoDoMetodoClear() {
        bufferTamanho0.clear();
        assertEquals(0, bufferTamanho0.capacity());
        assertEquals(bufferTamanho0.capacity(), bufferTamanho0.limit());
    }
    
    @Test
    void deveAlterarPosicaoELimiteAposExecucaoDoMetodoFlip() {
        bufferTamanho0.flip();
        assertEquals(bufferTamanho0.position(), bufferTamanho0.limit());
        assertEquals(0, bufferTamanho0.position());
    }
    
    @Test
    void deveConfirmarSeBufferPossuiArrayQuandoMetodoHasArrayForChamado() {
        assertTrue(bufferTamanho0.hasArray());
    }
    
    @Test
    void deveRetornarTrueQuandoMetodoHasRemainingConfirmarEspacosDisponiveis() {
        assertTrue(bufferTamanho10.position() < bufferTamanho10.limit());
    }
    
    @Test
    void deveConfirmarSeBufferEDeAlocacaoDireta() {
        assertTrue(bufferAlocacaoDireta.isDirect());
    }
    
    @Test
    void deveRetornarFalseQuandoMetodoIsReadOnlyConfirmarNaoReadOnly() {
        assertFalse(bufferTamanho0.isReadOnly());
    }
    
    @Test
    void deveRetornarZeroQuandoMetodoLimitForChamadoNoBufferTamanhoZero() {
        assertEquals(0, bufferTamanho0.limit());
    }
    
    @Test
    void deveAlterarLimiteDoBufferQuandoMetodoLimitComNovoLimiteForChamado() {
        int novoLimite = 5;
        bufferTamanho10.limit(novoLimite);
        assertEquals(novoLimite, bufferTamanho10.limit());
        assertThrows(IllegalArgumentException.class, () -> bufferTamanho10.limit(-1));
        assertThrows(IllegalArgumentException.class, () -> bufferTamanho10.limit(bufferTamanho10.capacity() + 1));
    }
    
    @Test
    void deveManterUltimaPosicaoMarcadaAposReset() {
        bufferTamanho10.position(5).mark();
        assertEquals(bufferTamanho10.reset().position(), bufferTamanho10.position());
        
        bufferTamanho10.position(2).mark();
        assertEquals(bufferTamanho10.reset().position(), bufferTamanho10.position());
    }
    
    @Test
    void deveRetornarZeroQuandoMetodoPositionForChamadoNoBufferTamanhoZero() {
        assertEquals(0, bufferTamanho0.position());
    }
    
    @Test
    void deveAlterarPosicaoDoBufferQuandoMetodoPositionComNovaPosicaoForChamado() {
        int novaPosicao = 5;
        bufferTamanho10.position(novaPosicao);
        assertEquals(novaPosicao, bufferTamanho10.position());
    }
    
    @Test
    void deveRetornarRemainingPositivoQuandoMetodoRemainingForChamado() {
        assertTrue(bufferTamanho0.remaining() >= 0);
    }
    
    @Test
    void deveRestaurarPosicaoAnteriorAoMarkQuandoMetodoResetForChamado() {
        bufferTamanho10.position(5).mark();
        
        bufferTamanho10.position(8);
        bufferTamanho10.reset();
        
        assertEquals(5, bufferTamanho10.position());
    }

    @Test
    void deveRestaurarPosicaoParaZeroQuandoMetodoRewindForChamado() {
        bufferTamanho10.position(5).mark();
        bufferTamanho10.rewind();
        assertEquals(0, bufferTamanho10.position());
    }
}
