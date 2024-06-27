import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private int idGerado;
    private List<Bloco> blocos;
    private Bloco ultimoBlocoModificado;

    public Memoria() {
        blocos = new ArrayList<>();
    }

    public void addBloco(Bloco bloco) {
        bloco.setId(geraId());
        blocos.add(bloco);
        ultimoBlocoModificado = null;
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public Bloco getBloco(int indice) {
        if (indice >= 0 && indice < blocos.size()) {
            return blocos.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites");
        }
    }

    public void atualizarBloco(Bloco blocoNovo) {
        for (int i = 0; i < blocos.size(); i++) {
            Bloco bloco = blocos.get(i);
            if (bloco.getId() == blocoNovo.getId()) {
                blocos.set(i, blocoNovo);
                ultimoBlocoModificado = blocoNovo;
                break;
            }
        }
    }

    public Bloco getUltimoBlocoModificado() {
        return ultimoBlocoModificado;
    }

    public void setUltimoBlocoModificado(Bloco ultimoBlocoModificado) {
        this.ultimoBlocoModificado = ultimoBlocoModificado;
    }

    private int geraId() {
        idGerado++;
        return idGerado;
    }
}
