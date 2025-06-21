package Query_Builder;

public abstract class BaseTable{
    protected Query query=new Query("");
    protected Condition condition=new Condition("");
    protected Operator operator=new Operator("");
    protected String placeholder="{}";
    protected TableName tableName;
    @Override
    public String toString(){
        return this.tableName.toString();
    }
    public void setTableName(TableName name){
        this.tableName=name;
    }
//    public BaseTable(String name){
//        this.tableName=new TableName(name);
//    }
//    protected String setAllColumns(String... columns){
//        StringBuilder Out= new StringBuilder();
//        for (String column :columns){
//            Out.append(column).append("={},");
//        }
//        return Out.toString().substring(0,Out.length()-1)+" ";
//    };
//
//    protected String andCondition(String... conditions){
//        StringBuilder Out= new StringBuilder();
//        for (String condition :conditions){
//            Out.append(condition).append("={} AND ");
//        }
//        return Out.toString().substring(0,Out.length()-4);
//    };
//
//    protected String updateRow(){
//        return "UPDATE "+this.tableName+" SET "+setAllColumns();
//    }
}
