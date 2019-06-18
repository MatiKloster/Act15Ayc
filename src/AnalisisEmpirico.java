import TDACola.EmptyQueueException;
import TDAColaPrioridad.Pesado;
import TDALista.DoubleLinkedList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
public class AnalisisEmpirico{

    public static void main(String[] args) throws Exception {
        Grafo grafo;
        DoubleLinkedList<ResultadoConexo> lista=new DoubleLinkedList<>();
        grafo = getGrafo(500,65000);
        analisisArbolMinimoCubrimiento(grafo);
//        lista.addFirst(analisisConectitudBFS(grafo));
//        lista.addFirst(analisisConectitudDS(grafo));
//        TimeUnit.MILLISECONDS.sleep(500);
//        grafo = getGrafo(50,49);
//        lista.addFirst(analisisConectitudBFS(grafo));
//        lista.addFirst(analisisConectitudDS(grafo));
//        TimeUnit.MILLISECONDS.sleep(500);
//        grafo = getGrafo(150,150);
//        lista.addFirst(analisisConectitudBFS(grafo));
//        lista.addFirst(analisisConectitudDS(grafo));
//        TimeUnit.MILLISECONDS.sleep(500);
//        grafo = getGrafo(333, 332);
//        lista.addFirst(analisisConectitudBFS(grafo));
//        lista.addFirst(analisisConectitudDS(grafo));
//        TimeUnit.MILLISECONDS.sleep(500);
//        grafo = getGrafo(456, 500);
//        lista.addFirst(analisisConectitudBFS(grafo));
//        lista.addFirst(analisisConectitudDS(grafo));
//        TimeUnit.MILLISECONDS.sleep(500);
//        grafo = getGrafo(500, 124750);
//        lista.addFirst(analisisConectitudBFS(grafo));
//        lista.addFirst(analisisConectitudDS(grafo));
//        TimeUnit.MILLISECONDS.sleep(500);
//        int i=1;
//        for(ResultadoConexo rs: lista){
//            System.out.println("Grafo numero:"+i+++", Conexo: "+rs.isResultado()+" time:"+rs.getTime()+"ns");
//        }
    }
    private static ResultadoConexo analisisConectitudDS(Grafo grafo) {
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

    private static void analisisArbolMinimoCubrimiento(Grafo grafo) throws Exception{
        Kruskal kruskal =new Kruskal(grafo);
        System.out.println("/////////Kruskal con Heap (sin heurísiticas)/////////");
        System.out.println("Se prodecera a obtener el arbol mínimo de cubrimiento con heap y controlando su tiempo:");
        Instant start= Instant.now();
        DoubleLinkedList<Pesado> resultado=kruskal.conHeapSH();
        Instant finish=Instant.now();
        long timeElapsed= Duration.between(start, finish).getNano();
        System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos resultados con Kruskal con heap:");
        System.out.print("Arbol: {");
        for (Pesado arco : resultado){
            System.out.print("("+arco.getArco().getNodo1()+","+arco.getArco().getNodo2()+")");
        }
        System.out.println("}");
        System.out.println("-Tiempo de ejecucion? -> "+timeElapsed+"ns");
        System.out.println("/////////Kruskal con Heap (con heurísticas)/////////");
        System.out.println("Se prodecera a obtener el arbol mínimo de cubrimiento con heap y controlando su tiempo:");
        start= Instant.now();
        resultado=kruskal.conHeap();
        finish=Instant.now();
        timeElapsed= Duration.between(start, finish).getNano();
        System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos resultados con Kruskal con heap:");
        System.out.print("Arbol: {");
        for (Pesado arco : resultado){
            System.out.print("("+arco.getArco().getNodo1()+","+arco.getArco().getNodo2()+")");
        }
        System.out.println("}");
        System.out.println("-Tiempo de ejecucion? -> "+timeElapsed+"ns");
        System.out.println("/////////Kruskal con Arcos ordenados (sin heurísiticas)/////////");
        System.out.println("Se prodecera a obtener el arbol mínimo de cubrimiento con arcos ordenados y controlando su tiempo:");
        start= Instant.now();
        resultado=kruskal.arcosOrdenadosSH();
        finish=Instant.now();
        timeElapsed= Duration.between(start, finish).getNano();
        System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos resultados con Kruskal con arcos ordenados:");
        System.out.print("Arbol: {");
        for (Pesado arco : resultado){
            System.out.print("("+arco.getArco().getNodo1()+","+arco.getArco().getNodo2()+")");
        }
        System.out.println("}");
        System.out.println("-Tiempo de ejecucion? -> "+timeElapsed+"ns");
        System.out.println("/////////Kruskal con Arcos ordenados (con heurísiticas)/////////");
        System.out.println("Se prodecera a obtener el arbol mínimo de cubrimiento con arcos ordenados y controlando su tiempo:");
        start= Instant.now();
        resultado=kruskal.arcosOrdenadosCH();
        finish=Instant.now();
        timeElapsed= Duration.between(start, finish).getNano();
        System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos resultados con Kruskal con arcos ordenados:");
        System.out.print("Arbol: {");
        for (Pesado arco : resultado){
            System.out.print("("+arco.getArco().getNodo1()+","+arco.getArco().getNodo2()+")");
        }
        System.out.println("}");
        System.out.println("-Tiempo de ejecucion? -> "+timeElapsed+"ns");
    }

    private static Grafo getGrafo(int nodos, int arcos) throws Exception {
        // TODO Auto-generated method stub
        String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo=1";
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