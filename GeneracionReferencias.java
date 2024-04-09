import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneracionReferencias {

    //ATRIBUTOS
    private int tamanioPagina; 
    private int tamanioMatriz;
    private ArrayList<String> registros;
    private int reg;

    //CONSTRUCTOR
    public GeneracionReferencias(int tamanioPagina, int tamanioMatriz){
        this.tamanioPagina = tamanioPagina;
        this.tamanioMatriz = tamanioMatriz;
        this.reg = 0;
        this.registros = new ArrayList<String>();
    }

    //CALCULAR NUMERO TOTAL DE PAGINAS
    public int calcularNumPagians(){

        float tamanioM = tamanioMatriz*tamanioMatriz*4;
        float tamanioF = 3*3*4;
        float tamanioTotal = tamanioM*2 + tamanioF;
        int numPaginas  = (int) Math.ceil(tamanioTotal/tamanioPagina);
        return numPaginas;
    }

    //CALCULAR PAGINA Y DESPLAZAMIENTO VIRTUAL
    public String calcularPaginaVirtual(int matriz, int fila, int columna){

        String respuesta = "";
        int inicio = 0;
        float dir = 0;
        
        if (matriz == 1){
            inicio = 0;
            dir = (fila*3*4 + columna*4);            
        }
        else if(matriz == 2){
            inicio = 3*3*4 ;
            dir = inicio + (fila*tamanioMatriz*4 + columna*4);
        }

        else if(matriz == 3){
            inicio = 3*3*4 + tamanioMatriz*tamanioMatriz*4;
            dir = inicio + (fila*tamanioMatriz*4 + columna*4);
        }

        if (dir != 0){
            float number = dir/tamanioPagina;
            int pag = (int) number;
            int des = (int)((number % 1)*(float)tamanioPagina);
            respuesta = pag + "," + des; 
        }
        else{
            respuesta = "0,0";
        }
        
        return respuesta;
    }

    //VERIFICAR SI UN DATO ESTA DIVIDIDO EN DOS PAGINAS
    public boolean datoDividido(String pagDes){



        String[] numbersAsString = pagDes.split(",");
        int desp =  Integer.parseInt(numbersAsString[1]);

        if (desp>(tamanioPagina-4)){
            return true;
        }
        return false;
    }

    //DECIR CUAL ES LA SIGUIENTE PAGINA
    public String nextPage(String pagDes){

        String[] numbersAsString = pagDes.split(",");
        int pag = Integer.parseInt(numbersAsString[0]) + 1;

        return pag + ",0";
    }

    //GENERAR REFERENCIAS
    public void generarReferencias(){

        String pagDes;

        for(int i=1; i<tamanioMatriz-1; i++){
            for(int j=1; j<tamanioMatriz-1; j++){

                for(int a=-1;a<=1;a++){
                    for(int b=-1;b<=1;b++){

                        int i2 = i+a;
                        int j2 = j+b;
                        int i3 = 1+a;
                        int j3 = 1+b;
                        

                        pagDes = calcularPaginaVirtual(2, i2, j2);
                        if (datoDividido(pagDes)){

                            registros.add("M["+ i2 + "][" + j2 + "]," + pagDes + ",R");
                            reg += 1;

                            registros.add("M["+ i2 + "][" + j2 + "]," + nextPage(pagDes) + ",R");
                            reg += 1;
                        }
                        else{
                            registros.add("M["+ i2 + "][" + j2 + "]," + pagDes + ",R");
                            reg += 1;
                        }


                        pagDes = calcularPaginaVirtual(1, i3, j3);
                        if (datoDividido(pagDes)){
                            registros.add("F["+ i3 + "][" + j3 + "]," + pagDes  + ",R");
                            reg += 1;
                            registros.add("F["+ i3 + "][" + j3 + "]," + nextPage(pagDes)  + ",R");
                            reg += 1;


                        }
                        else{
                            registros.add("F["+ i3 + "][" + j3 + "]," + pagDes  + ",R");
                            reg += 1;
                        }
                        
                    }
                }

                pagDes = calcularPaginaVirtual(3, i, j);
                if (datoDividido(pagDes)){

                    registros.add("R["+ i + "][" + j + "]," + pagDes  + ",W");
                    reg += 1;

                    registros.add("R["+ i + "][" + j + "]," + nextPage(pagDes) + ",W");
                    reg += 1;
                
                }
                else{
                    registros.add("R["+ i + "][" + j + "]," + pagDes  + ",W");
                    reg += 1;
                }
                
            }
        }

        for(int i=0; i<tamanioMatriz; i++){


            pagDes = calcularPaginaVirtual(3, 0, i);
            if (datoDividido(pagDes)){

                registros.add("R["+ 0 + "][" + i + "]," + pagDes  + ",W");
                reg += 1;

                registros.add("R["+ 0 + "][" + i + "]," +  nextPage(pagDes)   + ",W");
                reg += 1;
            }
            else{
                registros.add("R["+ 0 + "][" + i + "]," + pagDes  + ",W");
                reg += 1;
            }

            pagDes = calcularPaginaVirtual(3, tamanioMatriz-1, i);
            if(datoDividido(pagDes)){
                registros.add("R["+ (tamanioMatriz-1) + "][" + i + "]," + pagDes  + ",W");
                reg += 1;

                registros.add("R["+ (tamanioMatriz-1) + "][" + i + "]," + nextPage(pagDes)  + ",W");
                reg += 1;

            }
            else{
                registros.add("R["+ (tamanioMatriz-1) + "][" + i + "]," + pagDes  + ",W");
                reg += 1;
            }
            
        }

        for(int i=1; i<tamanioMatriz-1; i++){


            pagDes = calcularPaginaVirtual(3, i, 0);
            if(datoDividido(pagDes)){
                registros.add("R["+ i + "][" + 0 + "]," + pagDes + ",W");
                reg += 1;

                registros.add("R["+ i + "][" + 0 + "]," + nextPage(pagDes) + ",W");
                reg += 1;

            }
            else{
                registros.add("R["+ i + "][" + 0 + "]," + pagDes + ",W");
                reg += 1;
            }

            
            pagDes = calcularPaginaVirtual(3, i, tamanioMatriz-1);
            if(datoDividido(pagDes)){
                registros.add("R["+ i + "][" + (tamanioMatriz-1) + "]," + pagDes  + ",W");
                reg += 1;

                registros.add("R["+ i + "][" + (tamanioMatriz-1) + "]," + nextPage(pagDes)  + ",W");
                reg += 1;

            }
            else{
                registros.add("R["+ i + "][" + (tamanioMatriz-1) + "]," + pagDes  + ",W");
                reg += 1;
            }        
        }

    }


    //GENERAR ARCHIVO TXT
    public void generarArchivoReferencias(){

        //Generar las referencias
        generarReferencias();


        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Registros.txt"));
            writer.write("TP=" + tamanioPagina);
            writer.newLine();
            writer.write("NF="+ tamanioMatriz);
            writer.newLine();
            writer.write("NC="+ tamanioMatriz);
            writer.newLine();
            writer.write("NF_NC_Filtro=3");
            writer.newLine();
            writer.write("NR=" + reg);
            writer.newLine();
            writer.write("NP=" + calcularNumPagians());
            writer.newLine();

            for(int i=0; i<registros.size(); i++){
                writer.write(registros.get(i));
                writer.newLine();
            }
            
            writer.close();

        }catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }


    
       

        
    }

}
