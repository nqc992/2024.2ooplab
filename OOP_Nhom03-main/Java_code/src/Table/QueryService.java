package Table;

//import py4j.GatewayServer;

public class QueryService {


    public String getUserAccountPasswordQuery() {
        return UserAccount.getPasswordByEmailQuery();
    }

    public String getUserAccountUserIdQuery() {
        return UserAccount.getUserIdByEmailQuery();
    }

    public String getUserAccountUserNameQuery() {
        return UserAccount.getUserNameByIdQuery();
    }

    public String getUserAccountThemeQuery() {
        return UserAccount.getThemeByIdQuery();
    }

    public String getUserAccountSelectAllQuery() {

        return new UserAccount().selectAllQuery();
    }

    public String getUserAccountSelectByIdQuery() {
        return new UserAccount().selectByIdQuery();
    }

    public String getUserAccountInsertQuery() {
        return new UserAccount().insertQuery();
    }

    public String getUserAccountUpdateQuery() {
        return new UserAccount().updateQuery();
    }

    public String getUserAccountDeleteByIdQuery() {
        return new UserAccount().deleteByIdQuery();
    }

    // --- Queries cho Deck ---
    public String getDeckAvailableDeckIdsForUserQuery() {
        return Deck.getAvailableDeckIdsForUserQuery();
    }

    public String getDeckDecksByAuthorQuery() {
        return Deck.getDecksByAuthorQuery();
    }

    public String getDeckNameAndLanguageByIdQuery() {
        return Deck.getDeckNameAndLanguageByIdQuery();
    }

    public String getDeckBasicInfoByIdQuery() {
        return Deck.getDeckBasicInfoByIdQuery();
    }

    public String getDeckSelectAllQuery() {
        return new Deck().selectAllQuery();
    }

    // --- Queries cho Card ---
    public String getCardCardIdsByDeckIdQuery() {
        return Card.getCardIdsByDeckIdQuery();
    }

    public String getCardCountCardsInDeckQuery() {
        return Card.countCardsInDeckQuery();
    }

    public String getCardAllCardsByUserThroughDeckQuery() {
        return Card.getAllCardsByUserThroughDeckQuery();
    }

    public String getCardBasicInfoByIdQuery() {
        return Card.getCardBasicInfoByIdQuery();
    }

    public String getCardSelectAllQuery() {
        return new Card().selectAllQuery();
    }

    // --- Queries cho LearningProgress ---
    public String getLearningProgressForCardQuery() {
        return LearningProgress.getLearningProgressForCardQuery();
    }

    public String getLearningProgressCardsToReviewTodayQuery() {
        return LearningProgress.getCardsToReviewTodayQuery();
    }

    public String getLearningProgressCardsToReviewNext7DaysQuery() {
        return LearningProgress.getCardsToReviewNext7DaysQuery();
    }

    public String getLearningProgressCountLearnedCardsQuery() {
        return LearningProgress.countLearnedCardsQuery();
    }

    public String getLearningProgressSelectAllQuery() {
        return new LearningProgress().selectAllQuery();
    }

    // --- Queries cho DeckSetting ---
    public String getDeckSettingCardsPerDayQuery() {
        return DeckSetting.getCardsPerDayQuery();
    }
    public String getDeckSettingDeleteByCompositeIdQuery() {
        return DeckSetting.deleteByCompositeIdQuery();
    }
    public String getDeckSettingInsertQuery() {
        return new DeckSetting().insertQuery();
    }


    public String getDeckSettingSelectAllQuery() {
        return new DeckSetting().selectAllQuery();
    }


    public QueryGenerator getUserAccountQueryGeneratorInstance() {
        return new UserAccount();
    }
    public QueryGenerator getCardQueryGeneratorInstance() {
        return new Card();
    }
}