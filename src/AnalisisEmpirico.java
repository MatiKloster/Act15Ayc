import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AnalisisEmpirico{

    public static void main(String[] args) throws IOException {

        try{
            Conexo conexoHelper=new Conexo();
            Grafo grafo = getGrafo(10,45);
            System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos construido");
            System.out.println("/////////BFS/////////");
            System.out.println("Se prodecera a verificar que sea conexo utilizando BFS y controlando su tiempo:");
            Instant start= Instant.now();
            boolean resultado=conexoHelper.conexoBFS(grafo);
            Instant finish=Instant.now();
            long timeElapsed= Duration.between(start, finish).toMillis();
            System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos resultados con BFS:");
            System.out.println("-Es conexo? -> "+resultado);
            System.out.println("-Tiempo de ejecucion? -> "+timeElapsed);
            System.out.println("/////////Disjoint Set/////////");
            System.out.println("Se prodecera a verificar que sea conexo utilizando Disjoint Set y controlando su tiempo:");
            start= Instant.now();
            resultado=conexoHelper.conexoDS(grafo);
            finish=Instant.now();
            timeElapsed = Duration.between(start, finish).toMillis();
            System.out.println("Grafo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos resultados con Disjoint set:");
            System.out.println("-Es conexo? -> "+resultado);
            System.out.println("-Tiempo de ejecucion? -> "+timeElapsed);



            grafo.showGraph();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /*
         * Generar varios grafos de diferente configuración y buscar
         * árbol de cubrimiento minimal para cada uno.
         *
         * Medir el rendimiento usando timestamps.
         *
         */


    }
    private static Grafo getGrafo(int nodos, int arcos) throws Exception {
        // TODO Auto-generated method stub
        String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos;
        Process process = Runtime.getRuntime().exec(consulta);
        InputStream inputSt = process.getInputStream();
        @SuppressWarnings("resource")
        Scanner s = new Scanner(inputSt).useDelimiter("\\A");
        String jsonString = s.hasNext() ? s.next() : "";
        System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
        Gson gson = new GsonBuilder().create();
        try{
            Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
            return new Grafo(gr);
        } catch (Exception e) {
            throw new Exception(jsonString);
        }
    }
}