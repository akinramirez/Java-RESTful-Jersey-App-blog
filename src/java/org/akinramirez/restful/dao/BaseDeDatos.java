package org.akinramirez.restful.dao;

import java.util.ArrayList;
import java.util.List;
import org.akinramirez.restful.modelo.Articulo;
import org.akinramirez.restful.modelo.Comentario;

public class BaseDeDatos {

  private final static BaseDeDatos baseDeDatos = new BaseDeDatos();

  private final List<Articulo> listado = new ArrayList<>();

  private BaseDeDatos() {
    Articulo art1 = new Articulo(1, "Primer articulo", "Primer contenido", "Akin");
    Articulo art2 = new Articulo(2, "Segundo articulo", "Segundo contenido", "Arturo");
    
    art1.getComentarios().add(new Comentario(1, "Muy buen articulo 1", "Bilsan"));
    art1.getComentarios().add(new Comentario(2, "Muy buen articulo 2", "Enoc"));
    listado.add(art1);
    listado.add(art2);
  }

  public static BaseDeDatos getInstancia() {
    return baseDeDatos;
  }

  public List<Articulo> getListado(){
    return listado;
  }
  
  
}
