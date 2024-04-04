public class ManejadorTablaR extends Thread {

    private Tablasss tabla;
    private Menu menu;
    
    public ManejadorTablaR(Tablasss tabla, Menu menu) {
        this.tabla = tabla;
        this.menu = menu;
    }

    @Override
    public void run() {

        while (menu.continuar()) {

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tabla.resetBitR();
        }

    }
}
