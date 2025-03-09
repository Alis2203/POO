import java.io.*;
import java.util.*;

public class Main {
    private static List<Pokemon> pokedex = new ArrayList<>();
    private static final String FILE_PATH = "pokemon.csv";

    public static void main(String[] args) {
        cargarPokemons();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        System.out.println("(╯°□°)╯︵◓");

        do {
            mostrarMenu();
            System.out.print("Enter an option: ");
            opcion = scanner.nextLine().trim().toUpperCase();

            switch (opcion) {
                case "1":
                    marcarComoEncontrado(scanner);
                    break;
                case "2":
                    mostrarEncontrados();
                    break;
                case "3":
                    mostrarPorTipo(scanner);
                    break;
                case "4":
                    mostrarPokemon(scanner);
                    break;
                case "Q":
                    System.out.println("\nBye!");
                    guardarPokemons();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (!opcion.equals("Q"));

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Pokémon ---");
        System.out.println("1. Found Pokémon");
        System.out.println("2. Show found");
        System.out.println("3. Show type");
        System.out.println("4. Show Pokémon");
        System.out.println("Q. Exit");
    }

    private static void cargarPokemons() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                // Verificar si el CSV tiene ID (8 columnas) o no (7 columnas)
                if (datos.length == 7) {
                    // Formato original: Nombre,Tipo,HP,ATK,DEF,SPD,Encontrado
                    String nombre = datos[0];
                    String tipo = datos[1];
                    int hp = Integer.parseInt(datos[2]);
                    int atk = Integer.parseInt(datos[3]);
                    int def = Integer.parseInt(datos[4]);
                    int spd = Integer.parseInt(datos[5]);
                    boolean encontrado = Boolean.parseBoolean(datos[6]);

                    pokedex.add(new Pokemon(nombre, tipo, hp, atk, def, spd, encontrado));
                } else if (datos.length >= 8) {
                    // Formato modificado: ID,Nombre,Tipo,HP,ATK,DEF,SPD,Encontrado
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String tipo = datos[2];
                    int hp = Integer.parseInt(datos[3]);
                    int atk = Integer.parseInt(datos[4]);
                    int def = Integer.parseInt(datos[5]);
                    int spd = Integer.parseInt(datos[6]);
                    boolean encontrado = Boolean.parseBoolean(datos[7]);

                    pokedex.add(new Pokemon(id, nombre, tipo, hp, atk, def, spd, encontrado));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void marcarComoEncontrado(Scanner scanner) {
        System.out.print("\nEnter an identifier: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Pokemon p : pokedex) {
            if (p.getId() == id) {
                p.markAsFound();
                System.out.println("Found: " + p.getId() + " - " + p.getName());
                return;
            }
        }
        System.out.println("Pokémon not found.");
    }

    private static void mostrarEncontrados() {
        System.out.println();
        for (Pokemon p : pokedex) {
            if (p.isFound()) {
                System.out.println(p.getId() + " - " + p.getName() + " - " + p.getType());
            }
        }
    }

    private static void mostrarPorTipo(Scanner scanner) {
        System.out.println("\n--- Select type ---");
        Set<String> tipos = new HashSet<>();
        for (Pokemon p : pokedex) tipos.add(p.getType());
        List<String> listaTipos = new ArrayList<>(tipos);

        for (int i = 0; i < listaTipos.size(); i++) {
            System.out.println((i + 1) + ". " + listaTipos.get(i));
        }
        System.out.println("C. Cancel");

        System.out.print("Enter an option: ");
        String opcion = scanner.nextLine().trim().toUpperCase();
        if (opcion.equals("C")) return;

        try {
            int index = Integer.parseInt(opcion) - 1;
            if (index >= 0 && index < listaTipos.size()) {
                String tipoSeleccionado = listaTipos.get(index);
                for (Pokemon p : pokedex) {
                    if (p.getType().equals(tipoSeleccionado)) {
                        System.out.println(p.getId() + " - " + p.getName());
                    }
                }
            } else {
                System.out.println("Invalid option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void mostrarPokemon(Scanner scanner) {
        System.out.print("\nEnter an identifier: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Pokemon p : pokedex) {
            if (p.getId() == id) {
                // Con toString() sobrescrito se mostrará la información detallada
                System.out.println(p);
                return;
            }
        }
        System.out.println("Pokémon not found.");
    }

    private static void guardarPokemons() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Escribir cabecera con el campo ID
            bw.write("ID,Nombre,Tipo,HP,ATK,DEF,SPD,Encontrado\n");
            for (Pokemon p : pokedex) {
                String doc_line = p.getId() + "," + p.getName() + "," + p.getType() + "," +
                        p.getHp() + "," + p.getAttack() + "," + p.getDefense() + "," +
                        p.getSpeed() + "," + p.isFound();
                bw.write(doc_line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
