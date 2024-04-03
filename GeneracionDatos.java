import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GeneracionDatos {

    private int numeroMarcosPagina;
    private String archivoReferencias;
    private int numeroPaginas;
    private int numeroRegistros;
    private ArrayList<Integer> paginasReferenciadas;

    public GeneracionDatos(int numeroMarcosPagina, String archivoReferencias){
        this.numeroMarcosPagina = numeroMarcosPagina;
        this.archivoReferencias = archivoReferencias;
        this.paginasReferenciadas = new ArrayList<Integer>();
    }

    public void generarArchivoDatos(){

        try(BufferedReader reader = new BufferedReader(new FileReader(archivoReferencias))){

            //Leer datos del archivo que no nos importa
            reader.readLine(); 
            reader.readLine();
            reader.readLine();
            reader.readLine();
            

            numeroRegistros = Integer.parseInt(reader.readLine().replace("NR=", ""));
            numeroPaginas = Integer.parseInt(reader.readLine().replace("NP=", ""));

            for(int i=0; i<numeroRegistros; i++){

                paginasReferenciadas.add(Integer.parseInt(reader.readLine().split(",")[1]));
            }

            Tablasss tablas = new Tablasss(numeroPaginas, numeroMarcosPagina, paginasReferenciadas);
            tablas.simular();

        }catch(IOException e){
            e.printStackTrace();             
        }

    }
    
}
