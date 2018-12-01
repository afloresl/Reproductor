package com.example.reproductormusica;

public class Cancion {
    private int id;
    private String titulo;
    private String artista;

    public Cancion(int id, String titulo, String artista) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
    }
    public int getId(){return id;}
    public String getTitulo(){return titulo;}
    public String getArtista(){return artista;}

}
