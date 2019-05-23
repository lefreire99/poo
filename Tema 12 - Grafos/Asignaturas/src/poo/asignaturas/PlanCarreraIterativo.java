package poo.asignaturas;

import poo.asignaturas.siguientes.Siguientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación iterativa de la búsqueda del plan de carrera. Ésta será una búsqueda
 * en profundidad (depth first search) o en amplitud (breadth first search) según si
 * el atributo "siguientes" es una Pila o una Cola, respectivamente.
 */
public class PlanCarreraIterativo extends PlanCarrera {

  /**
   * Estructura de datos que guarda los nodos que se deben visitar en el futuro.
   */
  private Siguientes siguientes;

  /**
   * Construye un PlanCarrera Iterativo al que se le debe pasar una subclase de Siguientes,
   * pudiendo ser una Pila o una Cola.
   */
  public PlanCarreraIterativo(Siguientes siguientes) {
    this.siguientes = siguientes;
  }

  public List<Asignatura> queCursar(Asignatura a) {
    // sería mucho más óptimo usar un Set, pero usamos una lista para que
    // sea visible en qué orden se están visitando los nodos.
    List<Asignatura> visitadas = new ArrayList<>();
    siguientes.introducir(a);

    // Mientras la cola de siguientes a visitar no esté vacía:
    //   - sacamos un nodo (asignatura)
    //   - si no ha sido visitado ya, añadimos los nodos adyacentes a la asignatura
    //     a la lista de siguientes
    // Recordad que los nodos adyacentes representan las asignaturas que deben
    // cursarse inmediatamente antes de una asignatura dada.
    while (!siguientes.isVacia()) {
      Asignatura actual = siguientes.sacar();
      if (!visitadas.contains(actual)) {
        visitadas.add(actual);
        for (Asignatura adyacente : grafo.get(actual)) {
          siguientes.introducir(adyacente);
        }
      }
    }

    // para este ejemplo, sacamos la primera asignatura (la que queremos cursar)
    // ya que no tendría sentido algo como "para estudiar POO primero tienes que estudiar POO"
    visitadas.remove(0);
    return visitadas;
  }
}
