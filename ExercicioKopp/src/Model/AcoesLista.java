package Model;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 *
 * @author Eduardo
 */
public class AcoesLista {

    public UnaryOperator<String> removeS = texto -> texto.replaceAll("as", "a").replaceAll("seguintes", "seguinte").replaceAll("infrações", "infração");

    public BiFunction<Map<Integer, Double>, String, String> concat_infracoes = (lista, texto) -> {
        String infracao = "";
        for (int infra : lista.keySet()) {
            if (infra == lista.size() - 1) {
                infracao += infra + " e ";
            } else if (infra == lista.size()) {
                infracao += infra;
            } else {
                infracao += infra + ", ";
            }
        }
        texto += " " + infracao;
        return texto;
    };

    public BiFunction<Map<Integer, Double>, String, String> concat_uma_infracao = (lista, texto) -> {
        texto += " " + lista.keySet();
        texto = texto.replaceAll("\\[", "").replaceAll("\\]", "");
        return texto;
    };

    public BiFunction<Map<Integer, Double>, String, String> concat_valores = (lista, texto) -> {
        String infracao = "";
        double total = 0.0;
        for (int infra : lista.keySet()) {
            if (infra == lista.size() - 1) {
                infracao += infra + " cujo valor é R$ " + String.format("%.2f", lista.get(infra)) + " e ";
            } else if (infra == lista.size()) {
                infracao += infra + " cujo valor é R$ " + String.format("%.2f", lista.get(infra));
            } else {
                infracao += infra + " cujo valor é R$ " + String.format("%.2f", lista.get(infra)) + ", ";
            }

            total += lista.get(infra);
        }
        texto += " " + infracao + ". " + "Total= " + String.format("%.2f", total) + ".";
        return texto;
    };
}
