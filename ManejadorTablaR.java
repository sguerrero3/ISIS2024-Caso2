public class ManejadorTablaR extends Thread {

    private Tablasss tabla;

    public ManejadorTablaR(Tablasss tabla) {
        this.tabla = tabla;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            tabla.resetBitR();
        }

    }
}
