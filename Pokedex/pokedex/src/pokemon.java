public class Pokemon {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String type;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private boolean found;

    // Constructor original (sin ID, asigna automáticamente)
    public Pokemon(String name, String type, int hp, int attack, int defense, int speed, boolean found) {
        this.id = idCounter++;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.found = found;
    }

    // Constructor para cargar desde CSV (con ID)
    public Pokemon(int id, String name, String type, int hp, int attack, int defense, int speed, boolean found) {
        this.id = id;
        // Actualizar el contador si es necesario para evitar duplicados
        if (id >= idCounter) {
            idCounter = id + 1;
        }
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.found = found;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public boolean isFound() { return found; }

    // Método para marcar como encontrado
    public void markAsFound() {
        this.found = true;
    }

    // Método para generar las barras de progreso en la visualización de atributos
    private String barras(int value) {
        int totalBarras = 20;
        int barrasLlenas = (value * totalBarras) / 300;
        return "[" + "|".repeat(barrasLlenas) + " ".repeat(totalBarras - barrasLlenas) + "]";
    }

    // Método para mostrar información detallada del Pokémon
    public String mostrarInfo() {
        return "Pokémon: " + name + "\n" +
                "Tipo: " + type + "\n" +
                "HP  : " + String.format("%03d", hp) + "/300 " + barras(hp) + "\n" +
                "ATK : " + String.format("%03d", attack) + "/300 " + barras(attack) + "\n" +
                "DEF : " + String.format("%03d", defense) + "/300 " + barras(defense) + "\n" +
                "SPD : " + String.format("%03d", speed) + "/300 " + barras(speed);
    }

    // Sobrescribir toString para mostrar la información detallada
    @Override
    public String toString() {
        return mostrarInfo();
    }
}
