package Dominio.Modelo;


public class GranjaOficial {

    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private String cuentaFacebook;
    private String cuentaTwitter;
    private String cuentaInstagram;
    private String cuentaLinkedin;
    private String cuentaTikTok;

    public GranjaOficial(String nombre,
                         String descripcion,
                         String direccion,
                         String cuentaTikTok,
                         String cuentaInstagram,
                         String cuentaLinkedin,
                         String cuentaTwitter,
                         String cuentaFacebook,
                         String sitioWeb,
                         String email,
                         String telefono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.cuentaTikTok = cuentaTikTok;
        this.cuentaInstagram = cuentaInstagram;
        this.cuentaLinkedin = cuentaLinkedin;
        this.cuentaTwitter = cuentaTwitter;
        this.cuentaFacebook = cuentaFacebook;
        this.sitioWeb = sitioWeb;
        this.email = email;
        this.telefono = telefono;
    }

    public GranjaOficial() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getCuentaFacebook() {
        return cuentaFacebook;
    }

    public void setCuentaFacebook(String cuentaFacebook) {
        this.cuentaFacebook = cuentaFacebook;
    }

    public String getCuentaTwitter() {
        return cuentaTwitter;
    }

    public void setCuentaTwitter(String cuentaTwitter) {
        this.cuentaTwitter = cuentaTwitter;
    }

    public String getCuentaInstagram() {
        return cuentaInstagram;
    }

    public void setCuentaInstagram(String cuentaInstagram) {
        this.cuentaInstagram = cuentaInstagram;
    }

    public String getCuentaLinkedin() {
        return cuentaLinkedin;
    }

    public void setCuentaLinkedin(String cuentaLinkedin) {
        this.cuentaLinkedin = cuentaLinkedin;
    }

    public String getCuentaTikTok() {
        return cuentaTikTok;
    }

    public void setCuentaTikTok(String cuentaTikTok) {
        this.cuentaTikTok = cuentaTikTok;
    }
}
