import TDALista.DoubleLinkedList;
import business.Pesado;

public class ResultadoKruskal {
    private DoubleLinkedList<Pesado> arbolCub;
    private long time;
    private String examen;

    public ResultadoKruskal(DoubleLinkedList<Pesado> arbolCub, long time, String examen) {
        this.arbolCub = arbolCub;
        this.time = time;
        this.examen=examen;
    }

    public DoubleLinkedList<Pesado> getArbolCub() {
        return arbolCub;
    }

    public long getTime() {
        return time;
    }

    public String getExamen() {
        return examen;
    }
}
