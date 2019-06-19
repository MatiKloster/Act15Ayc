public class Resultado {
    private boolean resultado;
    private double time;

    public Resultado(boolean resultado, double time) {
        this.resultado = resultado;
        this.time=time;
    }

    public boolean isResultado() {
        return resultado;
    }

    public double getTime() {
        return time;
    }
}
