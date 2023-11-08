package Items;

import Diseases.Disease;
import Diseases.Healthy;
import Humans.Adult;
import Humans.God;
import Humans.Human;
import Places.Freedom;

import java.util.Objects;

public abstract class Readable {
    private ReadableType type;
    private String text;
    private String name;
    Readable(ReadableType type, String name, String text){
        this.type = type;
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return this.name;
    }

    public ReadableType getType() {
        return this.type;
    }

    public String getText(Human human){
        if (human instanceof Adult){
            System.out.println("Персонаж " + human.getName() + " умеет читать");
            System.out.println("Прочтен " + this.getName());
            return this.text;
        }
        System.out.println("Персонаж не умеет читать");
        return "Permission denied";
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() + this.name.hashCode() + this.text.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        Readable anotherReadable = (Readable) o;
        return Objects.equals(this.text, anotherReadable.text);
    }

    @Override
    public String toString() {
        return "Readable: {"
                + "Type = '" + this.getType() + '\''
                + "Name = " + this.getName() + '\''
                + "Text = " + this.getText(new God("name", new Healthy(), new Freedom())) + '\''
                + "Hash = " + this.hashCode()
                + '}';
    }
}
