import java.util.Scanner;

public class Menu {

    //ATRIBUTOS
    private Scanner scanner;

    //CONSTRUCTOR
    public Menu(Scanner scanner) {
        this.scanner = scanner;
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


    //INSTRUCCIONES DE QUE HACER CON CADA OPCION
    public void doOpciones(int opcion){

        switch(opcion){
            case 1:
                System.out.println("\n***Opcion Generar referencias***");
                opcionGenerarReferencias();
                break;

            case 2:
                System.out.println("\n***Opcion Calcular datos***");
                opcionCalcularDatos();
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

        GeneracionDatos generacionDatos = new GeneracionDatos(marcosPagina, archivoRef);
        generacionDatos.generarArchivoDatos();
    }


    //PROGRAMA PRINCIPAL
    public static void main(String[] args) {
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
