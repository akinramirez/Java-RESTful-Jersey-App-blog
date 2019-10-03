package org.akinramirez.restful.modelo;

public class Navegacion {

  private String titulo;
  private String link;
  private String descripcion;

  public Navegacion() {
  }

  public Navegacion(String titulo, String link, String descripcion) {
    this.titulo = titulo;
    this.link = link;
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

}
