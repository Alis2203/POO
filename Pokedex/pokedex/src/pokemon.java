public class pokemon {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String type;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private boolean found;

    // Constructor con todos los parámetros excepto el ID, que es autoincremental
    public pokemon(String name, String type, int hp, int attack, int defense, int speed, boolean found) {
        this.id = idCounter++;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.found = found; // Aquí es el valor que recibe como parámetro
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

    // Método para mostrar información del Pokémon
    public void displayInfo() {
        System.out.println("ID: " + id + " - " + name + " - " + type);
    }

    // Genera las barras para la visualización de los stats
    private String Barras(int value) {
        int largos_barras = 20;
        int largo_total = (value * largos_barras) / 300;
        return "[" + "|".repeat(largo_total) + " ".repeat(largos_barras - largo_total) + "]";
    }

    // Método para mostrar información detallada del Pokémon
    public String mostrarInfo() {
        return "Pokémon: " + name + "\n" +
                "Tipo: " + type + "\n" +
                "HP  : " + String.format("%03d", hp) + "/300 " + Barras(hp) + "\n" +
                "ATK : " + String.format("%03d", attack) + "/300 " + Barras(attack) + "\n" +
                "DEF : " + String.format("%03d", defense) + "/300 " + Barras(defense) + "\n" +
                "SPD : " + String.format("%03d", speed) + "/300 " + Barras(speed);
    }
}
