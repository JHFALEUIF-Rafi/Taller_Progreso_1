import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Cola {

    private static final int ASIENTO_MIN = 1;
    private static final int ASIENTO_MAX = 30;

    private final Queue<Pasajero> coleccion;

    public Cola() {
        coleccion = new LinkedList<>();
    }

    public void agregar(Pasajero p) {
        if (p == null) {
            throw new IllegalArgumentException("El pasajero no puede ser nulo");
        }

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del pasajero es obligatorio");
        }

        validarAsiento(p.getNumeroAsiento());

        for (Pasajero existente : coleccion) {
            if (existente.getNumeroAsiento() == p.getNumeroAsiento()) {
                throw new IllegalArgumentException("El asiento " + p.getNumeroAsiento()
                        + " no puedes brodi  " + existente.getNombre());
            }
        }

        coleccion.offer(p);
    }

    public Pasajero extraer() {
        if (coleccion.isEmpty()) {
            throw new NoSuchElementException("Cola sin elementos");
        }
        return coleccion.poll();
    }

    public Pasajero frente() {
        if (coleccion.isEmpty()) {
            throw new NoSuchElementException("Cola sin elementos");
        }
        return coleccion.peek();
    }

    public int size() {
        return coleccion.size();
    }

    public boolean estaVacia() {
        return coleccion.isEmpty();
    }

    public List<Pasajero> obtenerPendientes() {
        return Collections.unmodifiableList(new ArrayList<>(coleccion));
    }

    private void validarAsiento(int asiento) {
        if (asiento < ASIENTO_MIN || asiento > ASIENTO_MAX) {
            throw new IllegalArgumentException("El asiento debe estar entre "
                    + ASIENTO_MIN + " y " + ASIENTO_MAX);
        }
    }

    public Pasajero buscarPorAsiento(int asiento) {

        throw new UnsupportedOperationException("Pendiente implementar R2: búsqueda");
    }

    public int posicionPorAsiento(int asiento) {
        validarAsiento(asiento);

        int posicion = 1;
        for (Pasajero p : coleccion) {
            if (p.getNumeroAsiento() == asiento) {
                return posicion;
            }
            posicion++;
        }
        return -1;
    }

    public Pasajero cancelarPorAsiento(int asiento) {
        validarAsiento(asiento);
// es uninterador
        Iterator<Pasajero> it = coleccion.iterator();
        while (it.hasNext()) {
            Pasajero p = it.next();
            if (p.getNumeroAsiento() == asiento) {
                it.remove();
                return p;
            }
        }
        throw new NoSuchElementException("No hay un pasajero que quiera el puesto brodi " + asiento);
    }

    @Override
    public String toString() {
        if (coleccion.isEmpty()) {
            return "Cola sin elementos";
        }
        StringBuilder sb = new StringBuilder();
        int posicion = 1;
        for (Pasajero p : coleccion) {
            sb.append(posicion++).append(". ").append(p).append("\n");
        }
        return sb.toString();
    }
}