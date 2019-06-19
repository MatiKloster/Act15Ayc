import TDALista.DoubleLinkedList;
import business.Pesado;

public class ResultadoKruskal {
    private DoubleLinkedList<Pesado> arbolCub;
    private long time;

    public ResultadoKruskal(DoubleLinkedList<Pesado> arbolCub, long time) {
        this.arbolCub = arbolCub;
        this.time = time;
    }

    public DoubleLinkedList<Pesado> getArbolCub() {
        return arbolCub;
    }

    public long getTime() {
        return time;
    }
}
