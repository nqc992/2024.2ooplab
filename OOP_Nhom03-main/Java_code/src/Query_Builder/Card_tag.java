package Query_Builder;

public class Card_tag extends BaseTable implements InternalQuery{
    public final Key tag_id;//=new Key("tag_id");
    public final Key card_id;//=new Key("card_id");
    public final String tag="tag";

    public Card_tag(String name){
        //super();
        this.tableName=new TableName(name);
        this.tag_id=new Key("tag_id",name);
        this.card_id=new Key("card_id",name);
    };

    @Override
    public Query getDetailByKey() {
        return query.select(this.tag)
                .from(this)
                .where(condition.whereCondition(this.tag_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.whereCondition(this.tag_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.tag)
                .where(condition.whereCondition(this.tag_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.card_id.toString(),this.tag);
    }

//    @Override
//    public String updateRow(){
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.tag);
//    }
}
