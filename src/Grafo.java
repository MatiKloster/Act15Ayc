import TDALista.DoubleLinkedList;
import business.Pesado;

import java.util.ArrayList;

public class Grafo {
    private int[] nodos;
    private DoubleLinkedList<Pesado> arcos;
    private Pesado[][] matrizAdyacencia;


    public int getNodosCount(){
        return this.nodos.length;
    }

    public int getArcosCount(){
        return this.arcos.size();
    }

    @SuppressWarnings("rawtypes")
    public Grafo(GrafoObj grafoJson){
        this.nodos = grafoJson.nodos;
        this.arcos = new DoubleLinkedList();

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

            Pesado pesado = new Pesado(arcoLista.get(0), arcoLista.get(1), ((Double) arcosJson[i][1]).intValue());
            this.arcos.addFirst(pesado);
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
                if(matrizAdyacencia[i][j]==null)System.out.print("0   ");
                else System.out.print(matrizAdyacencia[i][j].getPeso()+" ");
            }
            System.out.println();
        }
    }

    public DoubleLinkedList<Pesado> getArcos(){
        return arcos;
    }

    public int[] getNodos(){
        return nodos;
    }

    public Pesado[] getAdyacentes(int n){
        return matrizAdyacencia[n];
    }
}