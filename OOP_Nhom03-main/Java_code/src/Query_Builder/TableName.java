package Query_Builder;


public class TableName{
    private final String name;
    public String getTableName() {
        return this.name;
    }
    public TableName(String name){
        this.name=name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
