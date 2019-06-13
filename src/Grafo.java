import java.util.ArrayList;

public class Grafo {
    private int[] nodos;
    private ArrayList<Pesado> arcos;
    private Pesado[][] matrizAdyacencia;

    private class Pesado {
        private Arco arco;
        private int peso;

        private Pesado(ArrayList<Integer> arcoLista, int peso) {
            // TODO Auto-generated constructor stub
            this.arco = new Arco(arcoLista.get(0), arcoLista.get(1));
            this.peso = peso;
        }

        private class Arco {
            private int nodo1;
            private int nodo2;

            public Arco(int i, int j) {
                this.nodo1 = i;
                this.nodo2 = j;
            }
        }
    }

    public int getNodosCount(){
        return this.nodos.length;
    }

    public int getArcosCount(){
        return this.arcos.size();
    }

    @SuppressWarnings("rawtypes")
    public Grafo(GrafoObj grafoJson){
        this.nodos = grafoJson.nodos;
        this.arcos = new ArrayList<Pesado>();

        int cantNodos=nodos.length;
        matrizAdyacencia=new Pesado[cantNodos][cantNodos];

        for(int i=0;i<cantNodos;i++){
            for(int j=0;j<cantNodos;j++){
                matrizAdyacencia[i][j]=null;
            }
        }

        Object[][] arcosJson = grafoJson.arcos;

        for (int i = 0; i<arcosJson.length; i++){

            ArrayList<Integer> arcoLista = new ArrayList<>();
            int nodoIzquierdo=((Double) ((ArrayList) arcosJson[i][0]).get(0)).intValue();
            int nodoDerecho=((Double) ((ArrayList) arcosJson[i][0]).get(1)).intValue();

            arcoLista.add(nodoIzquierdo);
            arcoLista.add(nodoDerecho);

            Pesado pesado = new Pesado(arcoLista, ((Double) arcosJson[i][1]).intValue());
            this.arcos.add(pesado);
            matrizAdyacencia[nodoIzquierdo][nodoDerecho]=pesado;
            matrizAdyacencia[nodoDerecho][nodoIzquierdo]=pesado;
        }
    }

    public static class GrafoObj {
        int[] nodos;
        Object[][] arcos;
    }
    public void showGraph(){
        for(int i=0;i<matrizAdyacencia.length;i++){
            for(int j=0;j<matrizAdyacencia.length;j++){
                if(matrizAdyacencia[i][j]==null)System.out.print("0 ");
                else System.out.print(matrizAdyacencia[i][j].peso+" ");
            }
            System.out.println();
        }
    }
}