package Query_Builder;

public class Deck_Tag extends BaseTable implements InternalQuery{
    public final Key tag_id;//=new Key("tag_id");
    public final Key deck_id;//=new Key("deck_id");
    public final String tag="tag";
    public Deck_Tag(String name){
        this.tableName=new TableName(name);
        this.tag_id=new Key("tag_id",name);
        this.deck_id=new Key("deck_id",name);
    }

    @Override
    public Query getDetailByKey() {
        return query.select(this.tag)
                .from(this)
                .where(condition.multiCondition(
                        condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.tag_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.multiCondition(
                        condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.tag,operator.Equal(),placeholder)));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.tag)
                .where(condition.multiCondition(
                        condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder),
                        operator.And(),
                        condition.whereCondition(this.tag_id.toString(),operator.Equal(),placeholder)));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.deck_id.toInsert(),this.tag);
    }

//    @Override
//    public String updateRow() {
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.tag);
//    }
}
