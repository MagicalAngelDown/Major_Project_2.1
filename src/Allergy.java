
public class Allergy implements Comparable<Allergy> {
    private String type;
    private String name;

    public Allergy(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public Allergy() {

    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Allergy Type: " + type + ", Name: " + name + "\n";
    }

    public int compareTo(Allergy otherAllergy){
        // return String.compare(this.type, otherAllergy.type);
        return this.type.compareTo(otherAllergy.type);
    }
}
