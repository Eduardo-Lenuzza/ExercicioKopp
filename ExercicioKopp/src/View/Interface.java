package View;

import Controller.ListaInfracoes;
import Model.AcoesLista;

/**
 *
 * @author Eduardo
 */
public class Interface {

    public static void main(String[] args) {

        String texto = "Remessa emitida com as seguintes infrações:";

        ListaInfracoes lista1 = new ListaInfracoes();
        lista1.getInfracoes().put(1, 88.00);
        lista1.getInfracoes().put(2, 130.00);
        lista1.getInfracoes().put(3, 88.00);
        lista1.getInfracoes().put(5, 88.00);
        lista1.getInfracoes().put(4, 293.00);

        AcoesLista acoes = new AcoesLista();

        System.out.println(acoes.concat_infracoes.apply(lista1.getInfracoes(), texto));

        ListaInfracoes lista2 = new ListaInfracoes();
        lista2.getInfracoes().put(1, 88.00);

        System.out.println(acoes.concat_uma_infracao.andThen(acoes.removeS).apply(lista2.getInfracoes(), texto));

        System.out.println(acoes.concat_valores.apply(lista1.getInfracoes(), texto));
    }
}
