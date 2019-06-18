import Exceptions.InvalidPositionException;
import TDACola.EmptyQueueException;
import TDALista.DoubleLinkedList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class AnalisisEmpirico{

    public static void main(String[] args) throws Exception {
        Grafo grafo;
        DoubleLinkedList<ResultadoConexo> lista=new DoubleLinkedList<>();
        grafo = getGrafo(5,4);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);
        grafo = getGrafo(50,49);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);
        grafo = getGrafo(150,150);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);
        grafo = getGrafo(333, 332);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);
        grafo = getGrafo(456, 500);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
       TimeUnit.MILLISECONDS.sleep(500);
        grafo = getGrafo(500, 124750);
        lista.addFirst(analisisConectitudDS(grafo));
        lista.addFirst(analisisConectitudBFS(grafo));
        TimeUnit.MILLISECONDS.sleep(500);
        int i=1;
        for(ResultadoConexo rs: lista){
            System.out.println("Grafo numero:"+i+++", Conexo: "+rs.isResultado()+" time:"+rs.getTime()+"ns");
        }
    }
    private static ResultadoConexo analisisConectitudDS(Grafo grafo) throws InvalidPositionException {
        Conexo conexoHelper = new Conexo();
        boolean resultado;
        long startTime = System.nanoTime();
        resultado = conexoHelper.conexoDS(grafo);
        long endtime = System.nanoTime();
        return new ResultadoConexo(resultado, endtime - startTime );
    }
    private static ResultadoConexo analisisConectitudBFS(Grafo grafo) throws EmptyQueueException {
        Conexo conexoHelper =new Conexo();
        boolean resultado;
        long  startTime = System.nanoTime();
        resultado=conexoHelper.conexoBFS(grafo);
        long endtime=System.nanoTime();
        return new ResultadoConexo(resultado,endtime-startTime);
    }

    private static Grafo getGrafo(int nodos, int arcos) throws Exception {
        // TODO Auto-generated method stub
        String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo=0";
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