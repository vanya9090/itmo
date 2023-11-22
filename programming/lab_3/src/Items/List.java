package Items;

public class List implements Readable{
    private final String name;
    private final String text;

    public List(String name, String text){
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
}
