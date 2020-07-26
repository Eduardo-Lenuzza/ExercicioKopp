package Model;

import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduardo
 */
public class AcoesListaTest {

    private AcoesLista acoes = null;
    private final String texto = "Remessa emitida com as seguintes infrações:";

    public AcoesListaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        acoes = new AcoesLista();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRemoveS() {
        assertEquals("Remessa emitida com a seguinte infração:", acoes.removeS.apply(texto));
    }

    @Test
    public void testConcat_infracoes() {
        Map<Integer, Double> lista = new TreeMap<>();
        lista.put(1, 88.00);
        lista.put(2, 130.00);
        assertEquals("Remessa emitida com as seguintes infrações: 1 e 2", acoes.concat_infracoes.apply(lista, texto));
    }

    @Test
    public void testConcat_uma_infracao() {
        Map<Integer, Double> lista = new TreeMap<>();
        lista.put(1, 88.00);
        assertEquals("Remessa emitida com a seguinte infração: 1", acoes.concat_uma_infracao.andThen(acoes.removeS).apply(lista, texto));
    }

    @Test
    public void testConcat_valores() {
        Map<Integer, Double> lista = new TreeMap<>();
        lista.put(1, 88.00);
        lista.put(2, 130.00);
        assertEquals("Remessa emitida com as seguintes infrações: 1 cujo valor é R$ 88,00 e 2 cujo valor é R$ 130,00. Total= 218,00.",
                acoes.concat_valores.apply(lista, texto));
    }
}
