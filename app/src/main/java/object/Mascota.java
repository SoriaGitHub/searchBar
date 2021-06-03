package object;

public class Mascota {
    private String nombre;
    private String edad;
    private String seo;

    public Mascota() {
    }

    public Mascota(String nombre, String edad, String seo) {
        this.nombre = nombre;
        this.edad = edad;
        this.seo = seo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }
}
