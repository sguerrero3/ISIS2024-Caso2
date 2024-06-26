import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Menu {

    //ATRIBUTOS
    private Scanner scanner;
    private CyclicBarrier barrera;
    private boolean continuar;

    //CONSTRUCTOR
    public Menu(Scanner scanner) {
        this.scanner = scanner;
        this.barrera = new CyclicBarrier(2);
        this.continuar = true;
    }

    //MOSTRAR MENU PRINCIPAL
    public void showMenuPrincipal(){

        System.out.println("Bienvenido al simulador de memoria");
        System.out.println("1. Generacion de referencias");
        System.out.println("2. Calcular datos");
        System.out.println("3. Salir");
        System.out.print("Opcion: ");

    }

    //GET OPCION
    public int getOpcion(){
        return scanner.nextInt();
    }

    public boolean continuar(){
        return continuar;
    }

    public void noContinuar(){
        continuar = false;
    }


    //INSTRUCCIONES DE QUE HACER CON CADA OPCION
    public void doOpciones(int opcion) throws InterruptedException, BrokenBarrierException{

        switch(opcion){
            case 1:
                System.out.println("\n***Opcion Generar referencias***");
                opcionGenerarReferencias();
                System.out.println("***Archivo generado con el nombre Registros.txt***\n");
                break;

            case 2:
                System.out.println("\n***Opcion Calcular datos***");
                opcionCalcularDatos();
                barrera.await();
                break;

            case 3:
                System.err.println("Saliendo del programa");
                break;
            
            default:
                System.out.println("Opcion no valida, vuelva a intentar");
                break;
        }
    }

    //OPCION GENERACION DE REFERENCIAS 
    public void opcionGenerarReferencias(){

        System.out.print("Indique el tamanio de la pagina (Bytes): ");
        int tamanioPagina = scanner.nextInt();
        System.out.print("Indique el tamanio de la matriz: ");
        int tamanioMatriz = scanner.nextInt();

        GeneracionReferencias generacionReferencias = new GeneracionReferencias(tamanioPagina, tamanioMatriz);

        generacionReferencias.generarArchivoReferencias();
    }

    public void opcionCalcularDatos(){

        System.out.print("Indique el numero de marcos de pagina: ");
        int marcosPagina = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Indique el nombre del archivo de referencias: ");
        String archivoRef = scanner.nextLine();

        continuar = true;

        GeneracionDatos generacionDatos = new GeneracionDatos(marcosPagina, archivoRef, this);
        generacionDatos.generarArchivoDatos();
    }

    public CyclicBarrier getBarrera(){
        return barrera;
    }

    //PROGRAMA PRINCIPAL
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);

        int opcion;

        do{
            menu.showMenuPrincipal();
            opcion = menu.getOpcion();
            menu.doOpciones(opcion);
        }while(opcion != 3);

        scanner.close();
        

    }
}
