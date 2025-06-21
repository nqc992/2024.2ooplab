package Query_Builder;

public class Query {
    private String query="";
    public Query(String q){
        this.query=q;
    }
    @Override
    public String toString(){
        return this.query;
    }
    public Query from(BaseTable table){
        return new Query(this.query+" FROM "+table.toString());
    }

    public BaseTable join(BaseTable table1,BaseTable table2,Condition con){

        return new TempTable(table1.toString() + " JOIN " + table2.toString() + " ON " + con.toString());
    }
    public BaseTable leftJoin(BaseTable table1,BaseTable table2,Condition con){

        return new TempTable(table1.toString() + " LEFT JOIN " + table2.toString() + " ON " + con.toString());
    }
    public BaseTable rightJoin(BaseTable table1,BaseTable table2,Condition con){

        return new TempTable(table1.toString() + " RIGHT JOIN " + table2.toString() + " ON " + con.toString());
    }

    protected String setAllColumns(String... columns){
        StringBuilder Out= new StringBuilder();
        for (String column :columns){
            Out.append(column).append("={},");
        }
        return Out.substring(0,Out.length()-1)+" ";
    };
    public Query where(Condition con){
        return new Query(this.query+" WHERE "+con.toString());
    }
    public String count(String col){
        return "COUNT("+col+")";
    }
    public String max(String col){
        return "MAX("+col+")";
    }
    public Query orderBy(Boolean ASC,String column){
        if (ASC){
            return new Query(this.query+ " ORDER BY "+column+" ASC");
        }
        return new Query(this.query+ " ORDER BY "+column+" DESC");
    }

    public Query select(String... columns){
        return new Query("SELECT "+String.join(", ",columns));
    }

    public Query update(BaseTable table,String... columns){
        return new Query("UPDATE "+table.toString()+" SET "+setAllColumns(columns));
    }

    public Query delete(BaseTable table){
        return new Query("DELETE FROM "+table.toString());
    }
    public Query insert(BaseTable table,String... columns){
        StringBuilder Cols= new StringBuilder();
        StringBuilder Vals=new StringBuilder();
        for (String column:columns){
            Cols.append(column).append(",");
            Vals.append("{},");
        }
        return new Query("INSERT INTO "+table.toString()+"("+Cols.substring(0,Cols.length()-1)+") "+"VALUES("+Vals.substring(0,Vals.length()-1)+")");
    }
    public Query limit(){
        return new Query(this.query+" LIMIT {}");
    }
}
