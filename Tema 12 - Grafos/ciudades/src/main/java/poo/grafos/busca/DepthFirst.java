package poo.grafos.busca;

import poo.grafos.Ciudad;
import poo.grafos.MapaCarreteras;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirst implements Buscador {
  @Override
  public List<Ciudad> ruta(Ciudad origen, Ciudad destino, MapaCarreteras mapa) {
    // Guardando aquí una ciudad, la marcamos como visitada
    Set<Ciudad> visitadas = new HashSet<>();

    // Pila de las ciudades que nos quedan por visitar
    List<Ciudad> pilaPorVisitar = new ArrayList<>();
    pilaPorVisitar.add(origen);

    // Vamos guardando los distintos caminos que se van recorriendo
    List<List<Ciudad>> pilaCaminos = new ArrayList<>();
    // Todo camino contendrá al menos la ciudad de origen
    List<Ciudad> camino = new ArrayList<>();
    camino.add(origen);
    pilaCaminos.add(camino);

    while (!pilaPorVisitar.isEmpty()) {
      // Sacamos la última ciudad que añadimos y el camino hasta ésta
      Ciudad actual = pilaPorVisitar.remove(pilaPorVisitar.size() - 1);
      camino = pilaCaminos.remove(pilaCaminos.size() - 1);

      // Si ya está visitada, vamos a la siguiente
      if (visitadas.contains(actual)) {
        continue;
      }
      visitadas.add(actual);

      if (actual.equals(destino)) {
        // Encontramos la ciudad de destino. Retornamos el camino trazado hasta ahora
        return camino;
      }

      // Si no, miramos todos los adyacentes
      for (Ciudad adyacente : mapa.getConexiones().get(actual)) {
        pilaPorVisitar.add(adyacente);
        // clonamos una nueva lista, igual al camino actual, y añadimos la ciudad adyacente
        List<Ciudad> clonCamino = new ArrayList<>(camino);
        clonCamino.add(adyacente);
        pilaCaminos.add(clonCamino);
      }

    }


    return null;
  }
}
