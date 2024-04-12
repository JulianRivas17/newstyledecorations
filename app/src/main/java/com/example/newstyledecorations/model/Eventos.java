package com.example.newstyledecorations.model;

public class Eventos {
    private String nombre;
    private String motivo;
    private String decoracion;
    private String tipoLuz;
    private String fecha;
    private String hora;
    private Integer edad;
    private Integer cantidadAccesorios;
    private boolean candyBar;
    private Integer cantidadPuff;
    private boolean globos;
    private boolean mesasDecorativas;
    private boolean mesaCilindro;
    private boolean mesaComun;
    private boolean luces;
    private boolean puff;
    private boolean fotos;
    private boolean recepcion;
    private boolean trono;
    private String colores;
    private String direccion;
    private String localidad;
    private boolean casa;
    private boolean salon;

    public Eventos() {}

    public Eventos(String nombre, String motivo, String decoracion, String tipoLuz, String fecha, String hora,
                        Integer edad, Integer cantidadAccesorios, boolean candyBar, Integer cantidadPuff, boolean globos,
                            boolean mesasDecorativas, boolean mesaCilindro, boolean mesaComun, boolean luces, boolean puff, boolean fotos,
                                boolean recepcion, boolean trono, String colores, String direccion, String localidad, boolean casa, boolean salon) {
        this.nombre = nombre;
        this.motivo = motivo;
        this.decoracion = decoracion;
        this.tipoLuz = tipoLuz;
        this.fecha = fecha;
        this.hora = hora;
        this.edad = edad;
        this.cantidadAccesorios = cantidadAccesorios;
        this.candyBar = candyBar;
        this.cantidadPuff = cantidadPuff;
        this.globos = globos;
        this.mesasDecorativas = mesasDecorativas;
        this.mesaCilindro = mesaCilindro;
        this.mesaComun = mesaComun;
        this.luces = luces;
        this.puff = puff;
        this.fotos = fotos;
        this.recepcion = recepcion;
        this.trono = trono;
        this.colores = colores;
        this.direccion = direccion;
        this.localidad = localidad;
        this.casa = casa;
        this.salon = salon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(String decoracion) {
        this.decoracion = decoracion;
    }

    public String getTipoLuz() {
        return tipoLuz;
    }

    public void setTipoLuz(String tipoLuz) {
        this.tipoLuz = tipoLuz;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getCantidadAccesorios() {
        return cantidadAccesorios;
    }

    public void setCantidadAccesorios(Integer cantidadAccesorios) {
        this.cantidadAccesorios = cantidadAccesorios;
    }

    public boolean isCandyBar() {
        return candyBar;
    }

    public void setCandyBar(boolean candyBar) {
        this.candyBar = candyBar;
    }

    public Integer getCantidadPuff() {
        return cantidadPuff;
    }

    public void setCantidadPuff(Integer cantidadPuff) {
        this.cantidadPuff = cantidadPuff;
    }

    public boolean isGlobos() {
        return globos;
    }

    public void setGlobos(boolean globos) {
        this.globos = globos;
    }

    public boolean isMesasDecorativas() {
        return mesasDecorativas;
    }

    public void setMesasDecorativas(boolean mesasDecorativas) {
        this.mesasDecorativas = mesasDecorativas;
    }

    public boolean isMesaCilindro() {
        return mesaCilindro;
    }

    public void setMesaCilindro(boolean mesaCilindro) {
        this.mesaCilindro = mesaCilindro;
    }

    public boolean isMesaComun() {
        return mesaComun;
    }

    public void setMesaComun(boolean mesaComun) {
        this.mesaComun = mesaComun;
    }

    public boolean isLuces() {
        return luces;
    }

    public void setLuces(boolean luces) {
        this.luces = luces;
    }

    public boolean isPuff() {
        return puff;
    }

    public void setPuff(boolean puff) {
        this.puff = puff;
    }

    public boolean isFotos() {
        return fotos;
    }

    public void setFotos(boolean fotos) {
        this.fotos = fotos;
    }

    public boolean isRecepcion() {
        return recepcion;
    }

    public void setRecepcion(boolean recepcion) {
        this.recepcion = recepcion;
    }

    public boolean isTrono() {
        return trono;
    }

    public void setTrono(boolean trono) {
        this.trono = trono;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public boolean isCasa() {
        return casa;
    }

    public void setCasa(boolean casa) {
        this.casa = casa;
    }

    public boolean isSalon() {
        return salon;
    }

    public void setSalon(boolean salon) {
        this.salon = salon;
    }
}
