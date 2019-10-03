package org.akinramirez.restful.servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.akinramirez.excepciones.DatosNoEncontradosExcepcion;
import org.akinramirez.restful.dao.BaseDeDatos;
import org.akinramirez.restful.modelo.Articulo;

public class ArticuloServicio {

  private final List<Articulo> listado = BaseDeDatos.getInstancia().getListado();

  public List<Articulo> getArticulos() {
    return listado;
  }

  public Articulo getArticulo(int id) {
    Articulo respuesta = null;
    for (Articulo articulo : listado) {
      if (articulo.getId() == id) {
        respuesta = articulo;
      }
    }
    if(respuesta == null){
      throw new DatosNoEncontradosExcepcion("No se encuentra el articulo");
    }
    return respuesta;
  }

  public List<Articulo> getArticuloPorAutor(String autor) {
    List<Articulo> resultado = new ArrayList<>();
    for (Articulo articulo : listado) {
      if (articulo.getAutor().equalsIgnoreCase(autor)) {
        resultado.add(articulo);
      }
    }
    return resultado;
  }

  public List<Articulo> getArticuloPorFecha(int year, int month) {
    List<Articulo> resultado = new ArrayList<>();
    Calendar fecha = new GregorianCalendar();
    for (Articulo articulo : listado) {
      fecha.setTime(articulo.getCreado().getTime());
      if (fecha.get(Calendar.YEAR) == year
              && fecha.get(Calendar.MONTH) == month - 1) {
        resultado.add(articulo);
      }
    }
    return resultado;
  }

  public Articulo addArticulo(Articulo articulo) {
    articulo.setId(getMaximo());
    listado.add(articulo);
    return articulo;
  }

  public Articulo updateArticulo(Articulo articulo) {
    int posicion = getPosicion(articulo.getId());
    try {
      listado.set(posicion, articulo);
    } catch (IndexOutOfBoundsException ioobe) {
      return null;
    }
    return articulo;
  }

  public void deleteArticulo(int id) {
    int posicion = getPosicion(id);
    listado.remove(posicion);

  }

  private int getPosicion(int id) {
    for (int i = 0; i < listado.size(); i++) {
      if (listado.get(i).getId() == id) {
        return i;
      }
    }
    return -1;
  }

  private int getMaximo() {
    int size = listado.size();
    if (size > 0) {
      return listado.get(size - 1).getId() + 1;
    } else {
      return 1;
    }
  }
}
