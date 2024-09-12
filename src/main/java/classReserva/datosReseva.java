package classReserva;
public class datosReseva {
 // Declaracion de variables que necesitamos usar 
    private int id;
    private String names;
    private String date;
    private String space;
    private String duration;

    // declaracion del constructor 
    public datosReseva(int id, String names, String date, String space, String duration) {
        this.id = id;
        this.names = names;
        this.date = date;
        this.space = space;
        this.duration = duration;
    }
    // declaracion de getter y setters 
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public datosReseva() {
    }

    public String getNames() {
        return names;
    }

    public String getDate() {
        return date;
    }

    public String getSpace() {
        return space;
    }

    public String getDuration() {
        return duration;
    }

    public void setNames(String names) {
        this.names = names;

    }

    public void setDate(String date) {
        this.date = date;

    }

    public void setSpace(String space) {
        this.space = space;

    }

    public void setDuration(String duration) {
        this.duration = duration;

    }

}
