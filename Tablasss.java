import java.util.ArrayList;
import java.util.Collections;

public class Tablasss {

    // ATRIBUTOS
    private int numeroPaginas;
    private int numeroMarcosPagina;
    private ArrayList<Integer> paginasReferenciadas;
    private ArrayList<Integer> tablaPaginas;
    private ArrayList<Integer> tablaBitR;
    private int numeroHit;
    private int numeroFallas;
    private int marcosAsignados;

    public Tablasss(int numeroPaginas, int numeroMarcosPagina, ArrayList<Integer> paginasReferenciadas) {

        this.numeroPaginas = numeroPaginas;
        this.numeroMarcosPagina = numeroMarcosPagina;
        this.paginasReferenciadas = paginasReferenciadas;
        this.tablaPaginas = new ArrayList<Integer>(Collections.nCopies(this.numeroPaginas, -1));
        this.tablaBitR = new ArrayList<Integer>(Collections.nCopies(this.numeroPaginas, 0));
        this.numeroHit = 0;
        this.numeroFallas = 0;
        this.marcosAsignados = 0;
    }

    public synchronized void buscar(int pagina) {

        if (tablaPaginas.get(pagina) == -1) {

            numeroFallas++;

            if (marcosAsignados == numeroMarcosPagina) {

                int sacar = -1;

                for (int i = 0; i < tablaBitR.size(); i++) {

                    int valor = tablaBitR.get(i);
                    int index = tablaPaginas.get(i);

                    if (valor == 0 && index != -1) {

                        sacar = i;
                        break;
                    }
                }

                tablaPaginas.set(sacar, -1);

            } else {

                marcosAsignados++;

            }

            tablaBitR.set(pagina, 1);
            tablaPaginas.set(pagina, 1);

        } else {

            numeroHit++;

        }
    }

    public synchronized void resetBitR() {
        tablaBitR = new ArrayList<Integer>(Collections.nCopies(this.numeroPaginas, 0));
    }

    public synchronized void terminado() {

        System.out.println(numeroFallas);
        System.out.println(numeroHit);

    }

    public void simular() {

        System.out.println("SIMULANDO");

        ManejadorTablaPaginas tb = new ManejadorTablaPaginas(this, paginasReferenciadas);
        tb.start();

        ManejadorTablaR tr = new ManejadorTablaR(this);
        tr.start();

    }

}
