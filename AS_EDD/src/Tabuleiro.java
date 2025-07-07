
import Model.Casa;
import Model.Inicio;
import Model.Imovel;
import Model.Imposto;
import Model.Restituicao;

import java.util.List;

public class Tabuleiro {
    private Casa inicio;

    public Tabuleiro(List<Imovel> imoveis, double salario) {
        montarTabuleiro(imoveis, salario);
    }

    private void montarTabuleiro(List<Imovel> imoveis, double salario) {

        Inicio casaInicio = new Inicio("Início", salario);
        Casa ultimaCasa = casaInicio;

        for (Imovel imovel : imoveis) {
            ultimaCasa.setProxima(imovel);
            ultimaCasa = imovel;
        }

        Imposto imposto = new Imposto("Imposto");
        ultimaCasa.setProxima(imposto);
        ultimaCasa = imposto;

        Restituicao restituicao = new Restituicao("Restituição", salario);
        ultimaCasa.setProxima(restituicao);
        ultimaCasa = restituicao;

        ultimaCasa.setProxima(casaInicio);

        this.inicio = casaInicio;
    }

    public Casa getInicio() {
        return inicio;
    }
}
