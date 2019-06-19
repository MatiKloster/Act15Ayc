import Exceptions.InvalidPositionException;
import TDACola.EmptyQueueException;
import TDALista.DoubleLinkedList;
import business.Pesado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AnalisisEmpirico{

    public static void main(String[] args) throws Exception {
        Grafo grafo;
        DoubleLinkedList<Resultado> lista=new DoubleLinkedList<>();
        System.out.println("Obteniendo 6 grafos, cargando ....");
        grafo = getGrafo(5,4,0);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);

        grafo = getGrafo(50,49,0);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);

        grafo = getGrafo(150,150,0);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);

        grafo = getGrafo(333, 54236,0);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);

        grafo = getGrafo(456, 100000,0);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);

        grafo = getGrafo(500, 124750,0);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("6 grafos verificando conectitud:");
        int i=1;
        for(Resultado rs: lista){
            System.out.println("Grafo numero:"+i+++", Conexo: "+rs.isResultado()+" time:"+rs.getTime()+"ns");
        }
        DoubleLinkedList<ResultadoKruskal> listaKruskal;
        System.out.println("Obteniendo 6 grafos conexos, cargando ....");

        grafo=getGrafo(5,4,1);
        mostrarKruskal(analisisArbolMinimoCubrimiento(grafo));
        grafo=getGrafo(50,49,1);
        mostrarKruskal(analisisArbolMinimoCubrimiento(grafo));
        grafo=getGrafo(150,150,1);
        mostrarKruskal(analisisArbolMinimoCubrimiento(grafo));
        grafo=getGrafo(333,54236,1);
        mostrarKruskal(analisisArbolMinimoCubrimiento(grafo));
        grafo=getGrafo(456,100000,1);
        mostrarKruskal(analisisArbolMinimoCubrimiento(grafo));
        grafo=getGrafo(500,124750,1);
        mostrarKruskal(analisisArbolMinimoCubrimiento(grafo));


        System.out.println("6 grafos conexos, obteniendo el arbol de cubrimiento minimal:");
    }

    private static void mostrarKruskal(DoubleLinkedList<ResultadoKruskal> resultadosKruskal) {
        int i=1;
        for(ResultadoKruskal res:resultadosKruskal){
            System.out.println("Time"+ i+++": "+res.getTime());
        }
    }

    private static Resultado analisisConectitudDS(Grafo grafo) throws InvalidPositionException {
        Conexo conexoHelper = new Conexo();
        boolean resultado;
        long startTime = System.nanoTime();
        resultado = conexoHelper.conexoDS(grafo);
        long endtime = System.nanoTime();
        return new Resultado(resultado, endtime - startTime );
    }
    private static Resultado analisisConectitudBFS(Grafo grafo) throws EmptyQueueException {
        Conexo conexoHelper =new Conexo();
        boolean resultado;
        long  startTime = System.nanoTime();
        resultado=conexoHelper.conexoBFS(grafo);
        long endtime=System.nanoTime();
        return new Resultado(resultado,endtime-startTime);
    }

    private static DoubleLinkedList<ResultadoKruskal> analisisArbolMinimoCubrimiento(Grafo grafo) throws Exception{
        Kruskal kruskal =new Kruskal(grafo);
        DoubleLinkedList<ResultadoKruskal> toRet=new DoubleLinkedList<>();
        DoubleLinkedList<Pesado> resultado;
        long  startTime;
        long endtime;
        startTime = System.nanoTime();
        resultado=kruskal.conHeapSH();
        endtime=System.nanoTime();
        toRet.addLast(new ResultadoKruskal(resultado,endtime-startTime));

        startTime = System.nanoTime();
        resultado=kruskal.conHeap();
        endtime=System.nanoTime();
        toRet.addLast(new ResultadoKruskal(resultado,endtime-startTime));

        startTime = System.nanoTime();
        resultado=kruskal.arcosOrdenadosSH();
        endtime=System.nanoTime();
        toRet.addLast(new ResultadoKruskal(resultado,endtime-startTime));

        startTime = System.nanoTime();
        resultado=kruskal.arcosOrdenadosCH();
        endtime=System.nanoTime();
        toRet.addLast(new ResultadoKruskal(resultado,endtime-startTime));

        return toRet;
    }

    private static Grafo getGrafo(int nodos, int arcos,int conexo) throws Exception {
        // TODO Auto-generated method stub
        String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo="+conexo;
        Process process = Runtime.getRuntime().exec(consulta);
        InputStream inputSt = process.getInputStream();
        @SuppressWarnings("resource")
        Scanner s = new Scanner(inputSt).useDelimiter("\\A");
        String jsonString = s.hasNext() ? s.next() : "";
        Gson gson = new GsonBuilder().create();
        try{
            Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
            return new Grafo(gr);
        } catch (Exception e) {
            throw new Exception(jsonString);
        }
    }
}