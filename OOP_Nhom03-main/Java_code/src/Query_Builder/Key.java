package Query_Builder;
public class Key{
    private final String name;
    private final String parent_table;
    public String getKeyName() {
        return this.name;
    }
    public Key(String name, String parentTable){
        this.name=name;
        this.parent_table = parentTable;
    }
    @Override
    public String toString(){
        return this.parent_table+"."+this.name;
    }

    public String toInsert(){
        return this.name;
    }
}
