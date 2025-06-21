package Query_Builder;

public class User_account extends BaseTable implements InternalQuery{
    public final Key user_id;//=new Key("user_id");
    public final String type="type";
    public final String theme="theme";
    public final String password="password";
    public final String user_name="user_name";
    public final String email="email";

    public User_account(String name){
        this.tableName=new TableName(name);
        this.user_id=new Key("user_id",name);
    }

    @Override
    public Query getDetailByKey() {
        return query.select(this.type,this.theme,this.user_name)
                .from(this)
                .where(condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.theme,this.user_name)
                .where(condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.type,this.theme,this.password,this.user_name,this.email);
    }
//    @Override
//    protected String updateRow(){
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.type,this.theme,this.password,this.user_name,this.email);
//    }
}
