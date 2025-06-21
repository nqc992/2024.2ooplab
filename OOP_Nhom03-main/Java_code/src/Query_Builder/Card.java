package Query_Builder;

public class Card extends BaseTable implements InternalQuery{
    public final Key card_id;// =new Key("card_id");
    public final Key deck_id;//=new Key("deck_id");
    public final String front="front";
    public final String back="back";

    public Card(String name){
        this.tableName=new TableName(name);
        this.card_id=new Key("card_id",name);
        this.deck_id=new Key("deck_id",name);
    };

    @Override
    public Query getDetailByKey() {
        return query.select(this.front,this.back)
                .from(this)
                .where(condition.whereCondition(this.card_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.whereCondition(this.card_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.front,this.back)
                .where(condition.whereCondition(this.card_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.deck_id.toInsert(),this.front,this.back);
    }

//    @Override
//    public String updateRow() {
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.front,this.back);
//    }
}
