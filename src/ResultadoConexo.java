public class ResultadoConexo {
    private boolean resultado;
    private double time;

    public ResultadoConexo(boolean resultado, double time) {
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
