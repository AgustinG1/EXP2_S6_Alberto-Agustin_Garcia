 package depurandoteatromoro;

import java.util.Scanner;

public class DepurandoTeatroMoro {
    static final int FILAS = 5;
    static final int COLUMNAS = 5;
    static char[][] asientos = new char[FILAS][COLUMNAS];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAsientos();
        int opcion;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> comprarEntrada();
                    case 2 -> reservarEntrada();
                    case 3 -> modificarVenta();
                    case 4 -> eliminarVenta();
                    case 5 -> mostrarAsientos();
                    case 6 -> generarBoleta();
                    case 7 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = 0;
            }
        } while (opcion != 7);
    }

    private static void inicializarAsientos() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                asientos[i][j] = 'L';
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Teatro Moro ---");
        System.out.println("1. Comprar entrada");
        System.out.println("2. Reservar entrada");
        System.out.println("3. Modificar venta");
        System.out.println("4. Eliminar venta");
        System.out.println("5. Mostrar asientos");
        System.out.println("6. Generar boleta");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void comprarEntrada() {
        int[] asiento = pedirAsiento();
        if (asiento == null) return;
        int fila = asiento[0], columna = asiento[1];

        if (asientos[fila][columna] == 'L') {
            asientos[fila][columna] = 'V';
            System.out.println("Entrada comprada exitosamente.");
        } else {
            System.out.println("El asiento no está disponible para compra.");
        }
    }

    private static void reservarEntrada() {
        int[] asiento = pedirAsiento();
        if (asiento == null) return;
        int fila = asiento[0], columna = asiento[1];

        if (asientos[fila][columna] == 'L') {
            asientos[fila][columna] = 'R';
            System.out.println("Asiento reservado exitosamente.");
        } else {
            System.out.println("El asiento no está disponible para reserva.");
        }
    }

    private static void modificarVenta() {
        int[] asiento = pedirAsiento();
        if (asiento == null) return;
        int fila = asiento[0], columna = asiento[1];

        if (asientos[fila][columna] == 'R') {
            asientos[fila][columna] = 'V';
            System.out.println("Reserva modificada a compra exitosamente.");
        } else {
            System.out.println("Solo se pueden modificar reservas.");
        }
    }

    private static void eliminarVenta() {
        int[] asiento = pedirAsiento();
        if (asiento == null) return;
        int fila = asiento[0], columna = asiento[1];

        if (asientos[fila][columna] == 'V' || asientos[fila][columna] == 'R') {
            asientos[fila][columna] = 'L';
            System.out.println("Venta/reserva eliminada exitosamente.");
        } else {
            System.out.println("No hay venta/reserva en ese asiento.");
        }
    }

    private static void mostrarAsientos() {
        System.out.println("\nMapa de Asientos:");
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(asientos[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void generarBoleta() {
        int[] asiento = pedirAsiento();
        if (asiento == null) return;
        mostrarBoleta(asiento[0], asiento[1]);
    }

    private static boolean esAsientoValido(int fila, int columna) {
        return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS;
    }

    private static int[] pedirAsiento() {
        try {
            System.out.print("Ingrese fila (0-" + (FILAS - 1) + "): ");
            int fila = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese columna (0-" + (COLUMNAS - 1) + "): ");
            int columna = Integer.parseInt(scanner.nextLine());
            if (esAsientoValido(fila, columna)) {
                return new int[]{fila, columna};
            } else {
                System.out.println("Asiento inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese valores numéricos válidos.");
        }
        return null;
    }

    private static void mostrarBoleta(int fila, int columna) {
        System.out.println("-------- BOLETA --------");
        System.out.println("Fila: " + fila);
        System.out.println("Columna: " + columna);
        System.out.println("Estado: " + asientos[fila][columna]);
        System.out.println("------------------------");
    }
}