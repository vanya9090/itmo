package Items;

import java.util.Objects;

public class PList implements Readable{
    private final String name;
    private final String text;

    public PList(String name, String text){
        this.name = name;
        this.text = text;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.text.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        PList anotherList = (PList) o;
        return Objects.equals(this.name, anotherList.name);
    }

    @Override
    public String toString() {
        return "List: {"
                + "Name = " + this.getName()
                + ", Text = " + this.getText()
                + ", Hash = " + this.hashCode()
                + '}';
    }
}
