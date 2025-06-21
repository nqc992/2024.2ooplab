package Query_Builder;

public class Learning_progress extends BaseTable implements InternalQuery{
    public final Key card_id;//=new Key("card_id");
    public final Key user_id;//=new Key("user_id");
    public final String confident="confident";
    public final String review_date="review_date";
    public Learning_progress(String name){
        this.tableName=new TableName(name);
        this.card_id=new Key("card_id",name);
        this.user_id=new Key("user_id",name);
    }

    @Override
    public Query getDetailByKey() {
        return query.select(this.confident,this.review_date)
                .from(this)
                .where(condition.multiCondition(
                        condition.whereCondition(this.card_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.multiCondition(
                        condition.whereCondition(this.card_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.confident,this.review_date)
                .where(condition.multiCondition(
                        condition.whereCondition(this.card_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.user_id.toString(),this.confident,this.review_date);
    }
//    @Override
//    protected String updateRow(){
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.confident,this.review_date);
//    }
}
