import java.util.ArrayList;

public class ManejadorTablaPaginas extends Thread{

    private Tablasss tabla;
    private ArrayList<Integer> paginasReferenciadas;

    public ManejadorTablaPaginas(Tablasss tabla, ArrayList<Integer> paginasReferenciadas){
        this.tabla = tabla;
        this.paginasReferenciadas = paginasReferenciadas;
    }

    @Override
    public void run(){

        for(int i=0; i<paginasReferenciadas.size(); i++){

            tabla.buscar(paginasReferenciadas.get(i));

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tabla.terminado();

    }
    
}
