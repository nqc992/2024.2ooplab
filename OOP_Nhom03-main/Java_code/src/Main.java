import LLM_Prompt.BackFlashcardPrompt;
import Query_Builder.*;
import Query_Builder.Card;
import Query_Builder.Deck;
import py4j.GatewayServer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    Card card=new Card("card");
    Card_tag card_tag=new Card_tag("card_tag");
    Deck deck=new Deck("deck");
    Deck_setting deck_setting=new Deck_setting("deck_setting");
    Deck_Tag deck_tag=new Deck_Tag("deck_tag");
    Learning_progress learning_progress=new Learning_progress("learning_progress");
    User_account user_account=new User_account("user_account");
    Query query=new Query("");
    Condition condition=new Condition("");
    Operator operator=new Operator("");
    String placeholder="{}";
    String current_date="current_date";
    String Null="";
    BackFlashcardPrompt backFlashcardPrompt=new BackFlashcardPrompt("");
//    List<Integer> Step= List.of(0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,18,19,21,22,24,26,27,29,31,
//            33,35,37,39,41,44,46,48,51,54,56,59,62,65,68,71,75,78,82,85,89,93,97,
//            102,106,111,115,120,125,130,136,141,147,153,160,166,173,180,187,194,202,
//            210,218,226,235,244,254,263,273,284,295,306,317,329,342,354,368,381,396,
//            410,426,441,458,475,492,510,529,548,568,589,611,633,656,680,704,730);
    public String SayHello(String name){
        return "This String is from Java"+". Hello "+name;
    }
    public String get_UserPassword(){
        return query.select(user_account.password)
                .from(user_account)
                .where(condition.whereCondition(user_account.email,operator.Equal(),placeholder)).toString();
    }

    public String get_UserId(){
        return query.select(user_account.user_id.toString())
                .from(user_account)
                .where(condition.whereCondition(user_account.email,operator.Equal(),placeholder)).toString();
    }
    public String get_AuthorName(){
        return query.select(user_account.user_name)
                .from(user_account)
                .where(condition.whereCondition(user_account.user_id.toString(),operator.Equal(),placeholder)).toString();
    }
    public String get_DeckIdList(){
        return query.select(deck.deck_id.toString())
                .from(deck)
                .where(condition.multiCondition(
                        condition.whereCondition(deck.author_id.toString(),operator.Equal(),placeholder),
                        operator.Or(),
                        condition.whereCondition(deck.isPublic,operator.Equal(),"true"))).toString();
    }
    public String get_CardIdList(){
        return query.select(card.card_id.toString())
                .from(card)
                .where(condition.whereCondition(
                        card.deck_id.toString(),
                        operator.Equal(),
                        placeholder)).toString();
    }

    public String get_UserIdByEmail(){
        return query.select(user_account.user_id.toString())
                .from(user_account)
                .where(condition.whereCondition(user_account.email,operator.Equal(),placeholder)).limit().toString();
    }

    public String get_DeckDetail(){
        return deck.getDetailByKey().toString();
    }
    public String get_CardDetail(){
        return card.getDetailByKey().toString();
    }

    public String update_CardBackByCardId(){
        return query.update(card,card.back)
                .where(condition.whereCondition(
                        card.card_id.toString(),
                        operator.Equal(),
                        placeholder)).toString();
    }

    public String get_CardTagByCardId() {
        return query.select(card_tag.tag)
                .from(card_tag)
                .where(condition.whereCondition(
                        card_tag.card_id.toString(),
                        operator.Equal(),
                        placeholder)).orderBy(true,card_tag.tag).toString();
    }
    public String get_DeckTagByDeckId() {
        return query.select(deck_tag.tag)
                .from(deck_tag)
                .where(condition.whereCondition(
                        deck_tag.deck_id.toString(),
                        operator.Equal(),
                        placeholder)).orderBy(true,deck_tag.tag).toString();
    }

    public String get_BackCardPrompt(String tag,String front,String responseLanguage){
        return backFlashcardPrompt.prompt(tag,front,responseLanguage);
    }

    public String get_CardIdReviewDateNotInFuture(){
        return query.select(learning_progress.card_id.toString())
                .from(learning_progress)
                .where(condition.whereCondition(
                        learning_progress.review_date,
                        operator.SmallerOrEqual(),
                        placeholder)).toString();
    }

    public  String get_CardIdReviewDateIsNull(){
        return query.select(card.card_id.toString()).
                from(query.leftJoin(card,learning_progress,
                        condition.joinCondition(
                                card.card_id,
                                operator.Equal(),
                                learning_progress.card_id))).toString();
    }
    public String get_CardIdBackNull(){
        return query.select(card.card_id.toString())
                .from(card)
                .where(condition.multiCondition(
                        condition.whereCondition(
                                card.back,
                                operator.Equal(),
                                "''"),
                        operator.And(),
                        condition.whereCondition(
                                card.deck_id.toString(),
                                operator.Equal(),
                                placeholder))).toString();
    }
    public String get_CardFrontByCardId(){
        return query.select(card.front)
                .from(card)
                .where(condition.whereCondition(
                        card.card_id.toString(),
                        operator.Equal(),
                        placeholder)).toString();
    }
    public String get_CardIdHavingReviewDateLessThanNowByDeckId(){
        return query.select(card.card_id.toString())
                .from(query.join(card,learning_progress,
                        condition.joinCondition(
                                card.card_id,
                                operator.Equal(),
                                learning_progress.card_id)))
                .where(condition.multiCondition(
                        condition.whereCondition(
                                card.deck_id.toString(),
                                operator.Equal(),
                                placeholder),
                        operator.And(),
                        condition.whereCondition(
                                learning_progress.review_date,
                                operator.SmallerOrEqual(),
                                current_date))).toString();
    }
    public String get_CardIdNotHavingReviewDateByDeckId(){
        return query.select(card.card_id.toString())
                .from(query.leftJoin(card,learning_progress,
                        condition.joinCondition(
                                card.card_id,
                                operator.Equal(),
                                learning_progress.card_id)))
                .where(condition.multiCondition(
                        condition.whereCondition(
                                card.deck_id.toString(),
                                operator.Equal(),
                                placeholder),
                        operator.And(),
                        condition.whereCondition(
                                learning_progress.review_date,
                                operator.IsNull(),
                                Null))).toString();
    }
    public String get_CardIdHavingReviewDateGreaterThanNowByDeckId(){
        return query.select(card.card_id.toString())
                .from(query.join(card,learning_progress,
                        condition.joinCondition(
                                card.card_id,
                                operator.Equal(),
                                learning_progress.card_id)))
                .where(condition.multiCondition(
                        condition.whereCondition(
                                card.deck_id.toString(),
                                operator.Equal(),
                                placeholder),
                        operator.And(),
                        condition.whereCondition(
                                learning_progress.review_date,
                                operator.Greater(),
                                current_date))).toString();
    }
    public String get_CustomDeckInfoByDeckIdAndUserId(){
        return query.select(deck_setting.learning_pace,deck_setting.card_per_day)
                .from(deck_setting)
                .where(condition.multiCondition(
                        condition.whereCondition(
                                deck_setting.deck_id.toString(),
                                operator.Equal(),
                                placeholder),
                        operator.And(),
                        condition.whereCondition(
                                deck_setting.user_id.toString(),
                                operator.Equal(),
                                placeholder)
                )).toString();
    }
    public String update_CustomDeckInfoByDeckIdAndUserId(){
        return query.update(deck_setting,deck_setting.learning_pace,deck_setting.card_per_day)
                .where(condition.multiCondition(
                condition.whereCondition(
                        deck_setting.deck_id.toString(),
                        operator.Equal(),
                        placeholder),
                operator.And(),
                condition.whereCondition(
                        deck_setting.user_id.toString(),
                        operator.Equal(),
                        placeholder)
        )).toString();
    }
    public String insert_CustomDeckInfoByDeckIdAndUserId(){
        return query.insert(deck_setting,
                deck_setting.learning_pace,
                deck_setting.card_per_day,
                deck_setting.deck_id.toInsert(),
                deck_setting.user_id.toInsert()).toString();
    }
    public String get_UserDetailById(){
        return user_account.getDetailByKey().toString();
    }
    public String update_UserDetailById(){
        return user_account.updateDetailByKey().toString();
    }
    public String get_ConfidentByCardIdAndUserId(){
        return query.select(learning_progress.confident).from(learning_progress)
                .where(condition.multiCondition(
                        condition.whereCondition(learning_progress.card_id.toString(),
                                operator.Equal(),
                                placeholder),
                        operator.And(),
                        condition.whereCondition(learning_progress.user_id.toString(),
                                operator.Equal(),
                                placeholder)
                )).toString();
    }
    public String insert_LearningProgress(){
        return query.insert(learning_progress,
                learning_progress.card_id.toInsert(),
                learning_progress.user_id.toInsert()).toString();
    }
    public String update_LearningProgress(){
        return query.update(learning_progress,
                learning_progress.confident,
                learning_progress.review_date)
                .where(condition.multiCondition(
                        condition.whereCondition(
                                learning_progress.card_id.toString(),
                                operator.Equal(),
                                placeholder),
                        operator.And(),
                        condition.whereCondition(
                                learning_progress.user_id.toString(),
                                operator.Equal(),
                                placeholder
                        )
                )).toString();
    }
    public String insert_Deck(){
        return deck.insertDetail().toString();
    }
    public String insert_Card(){
        return card.insertDetail().toString();
    }
    public String update_Deck(){
        return deck.updateDetailByKey().toString();
    }
    public String update_DeckSetting(){
        return deck_setting.updateDetailByKey().toString();
    }
    public String delete_DeckTag(){
        return deck_tag.deleteByKey().toString();
    }
    public String insert_DeckTag(){
        return deck_tag.insertDetail().toString();
    }
    public static void main(String[] args) {
        GatewayServer gatewayServer =new GatewayServer(new Main(),25333);
        gatewayServer.start();
        System.out.println("Gateway Server Started");
//        Main main=new Main();
//        System.out.println(main.get_UserIdByEmail());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.get_UserPassword());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.get_UserId());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.get_AuthorName());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.get_DeckIdList());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.get_CardIdList());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//
//        System.out.println(main.card.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card_tag.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card_tag.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card_tag.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.card_tag.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_setting.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_setting.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_setting.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_setting.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_tag.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_tag.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_tag.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.deck_tag.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.learning_progress.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.learning_progress.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.learning_progress.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.learning_progress.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.user_account.getDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.user_account.updateDetailByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.user_account.deleteByKey());
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main.user_account.insertDetail());
//
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main);
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main);
//        System.out.println("- - - - - - - - - - - - - - - - - - -");
//        System.out.println(main);
//        System.out.println("- - - - - - - - - - - - - - - - - - -");




    }
}


