import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ManejadorTablaPaginas extends Thread{

    private Tablasss tabla;
    private ArrayList<Integer> paginasReferenciadas;
    private CyclicBarrier barrera;

    public ManejadorTablaPaginas(Tablasss tabla, ArrayList<Integer> paginasReferenciadas, Menu menu){
        this.tabla = tabla;
        this.paginasReferenciadas = paginasReferenciadas;
        this.barrera = menu.getBarrera();
    }

    @Override
    public void run(){

        for(int i=0; i<paginasReferenciadas.size(); i++){

            tabla.buscar(paginasReferenciadas.get(i));

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tabla.terminado();

        try {
            barrera.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    
}
