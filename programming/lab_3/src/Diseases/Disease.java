package Diseases;

public abstract class Disease {
    private String name;
    private DiseaseType type;
    private int degree;
    Disease(DiseaseType type, int degree, String name){
        this.type = type;
        this.degree = degree;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DiseaseType getType(){
        return this.type;
    }

    public int getDegree(){
        return this.degree;
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() + this.degree;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        Disease anotherDisease = (Disease) o;
        return this.type == anotherDisease.type;
    }

    @Override
    public String toString() {
        return "Disease: {"
                + "Disease type = '" + this.getType() + '\''
                + "Degree = " + this.getDegree() + '\''
                + "Hash = " + this.hashCode()
                + '}';
    }
}
