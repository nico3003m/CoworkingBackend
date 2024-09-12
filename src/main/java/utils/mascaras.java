// Estas mascaras se utulizaron dependiendo la necesidad de que datos se le mostraban al usuario 
package utils;


public class mascaras {
    private String transformation;
    
    
    public  String mascaraSpace(String data){
        
        
        switch (data) {
            case "escritorio":
                transformation = "Escritorio";
                break;
            case "saladereuniones":
                transformation = "Sala de Reunion";
                break;
                
            case "oficinaprivada":
                transformation = "Oficina Privada";
                break;
            default:
                throw new AssertionError();
        }
        return transformation  ; 
    }
     public  String mascaraDuration(String data){
        
        
        switch (data) {
            case "0.5":
                transformation = "30 minutos";
                break;
            case "1.0":
                transformation = "1 hora ";
                break;
                
            case "1.5":
                transformation = "1 hora y 30  minutos ";
                break;
             case "2.0":
                transformation = "2 horas ";
                break;
            default:
                throw new AssertionError();
        }
        return transformation  ; 
    }
}
