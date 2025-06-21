package Query_Builder;

public class Deck extends BaseTable implements InternalQuery{
    public final Key deck_id;//=new Key("deck_id");
    public final Key author_id;//=new Key("author_id");
    public final String deck_name="deck_name";
    public final String isPublic = "public";
    public final String back_lang="back_lang";
    public Deck(String name){
        this.tableName=new TableName(name);
        this.deck_id=new Key("deck_id",name);
        this.author_id=new Key("author_id",name);
    };

    @Override
    public Query getDetailByKey() {
        return query.select(this.deck_name,this.isPublic,this.back_lang,this.author_id.toString())
                .from(this)
                .where(condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query deleteByKey() {
        return query.delete(this)
                .where(condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query updateDetailByKey() {
        return query.update(this,this.deck_name,this.isPublic,this.back_lang)
                .where(condition.whereCondition(this.deck_id.toString(),operator.Equal(),placeholder));
    }

    @Override
    public Query insertDetail() {
        return query.insert(this,this.author_id.toInsert(),this.deck_name,this.isPublic,this.back_lang);
    }

//    @Override
//    public String updateRow() {
//        return "UPDATE "+this.tableName+" SET "+setAllColumns(this.deck_name,this.isPublic,this.back_lang);
//    }

}
