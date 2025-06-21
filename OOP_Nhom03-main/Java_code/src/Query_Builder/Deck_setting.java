package Query_Builder;

public class Deck_setting extends BaseTable implements InternalQuery{
    public final Key deck_id;//=new Key("deck_id");
    public final Key user_id;//=new Key("user_id");
    public final String learning_pace="learning_pace";
    public final String card_per_day="card_per_day";

    public Deck_setting(String name){
        this.tableName=new TableName(name);
        this.deck_id=new Key("deck_id",name);
        this.user_id=new Key("user_id",name);
    }

    @Override
    public Query getDetailByKey() {
        return query.select(this.learning_pace,this.card_per_day)
                .from(this)
                .where(condition.multiCondition(
                        condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.multiCondition(
                        condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.learning_pace,this.card_per_day)
                .where(condition.multiCondition(
                        condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.user_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.user_id.toInsert(),this.learning_pace,this.card_per_day);
    }

//    @Override
//    public String updateRow() {
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.learning_pace,this.card_per_day);
//    }
}
