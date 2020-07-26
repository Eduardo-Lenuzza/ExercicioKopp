package Controller;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Eduardo
 */
public class ListaInfracoes {

    private Map<Integer, Double> infracoes;

    public ListaInfracoes() {
        this.infracoes = new TreeMap<>();
    }

    public Map<Integer, Double> getInfracoes() {
        return infracoes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListaInfracoes other = (ListaInfracoes) obj;
        return true;
    }
}
