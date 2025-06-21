# from mysqlx import Row
import psycopg2
import ProtectedData
from py4j.java_gateway import JavaGateway, GatewayParameters
from LLM_API import FreePrompt
from nicegui import app as nicegui_app
from bs4 import BeautifulSoup
from markdown import markdown


def GetGateway():
    return JavaGateway(gateway_parameters=GatewayParameters(port=25333))


def toString(s: str):
    return "'{}'".format(s)


def checkedString(back: str):
    # html = markdown(back)
    # back = "".join(BeautifulSoup(html, features="html.parser").findAll(text=True))
    back = "''".join(back.split("'"))
    return toString(back)


def RunQuery(query: str):
    DB_info = ProtectedData.getDatabaseKey()
    conn = psycopg2.connect(
        database=DB_info["DB_NAME"],
        password=DB_info["DB_PASS"],
        user=DB_info["DB_USER"],
        host=DB_info["DB_HOST"],
        port=DB_info["DB_PORT"],
    )
    print("Connected successfully---")
    # conn = nicegui_app.storage.general.get("db_conn")
    # print(type(conn), "<----")
    print("->{}<-".format(query))
    print("----------------------")
    cur = conn.cursor()
    stage = cur.execute(query)
    try:
        rows = cur.fetchall()
        print(rows)
    except:
        rows = "-No results-"
        # print("Stage:", stage)
        # input()
    finally:
        cur.close()
        conn.commit()
        conn.close()
        return rows


def get_UserPassword(email: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_UserPassword().format(toString(email)))
    if len(rows) == 0:
        return None
    return rows[0][0]


def get_UserId(email: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_UserId().format(toString(email)))
    if len(rows) == 0:
        return None
    return rows[0][0]


def get_AuthorName(userId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_AuthorName().format(userId))
    if len(rows) == 0:
        return "Unknown author"
    return rows[0][0]


def get_DeckIdList(userId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_DeckIdList().format(userId))
    if len(rows) == 0:
        return None
    return list(i[0] for i in rows)


def get_CardIdList(deckId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_CardIdList().format(deckId))
    if len(rows) == 0:
        return None
    return list(i[0] for i in rows)


def get_DeckDetail(deckId: str, userId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_DeckDetail().format(deckId))
    if len(rows) == 0:
        return None
    Output = dict()
    Output["BackLanguage"] = rows[0][2]
    Output["Title"] = rows[0][0]
    Output["ViewType"] = "public" if rows[0][1] else "private"
    Output["Author"] = get_AuthorName(rows[0][3])
    Output["AuthorId"] = rows[0][3]
    Output["Tag"] = get_DeckTagByDeckId(deckId)
    Output["deckId"] = deckId
    Output.update(get_CustomDeckInfoByDeckIdAndUserId(deckId, userId))
    return Output


def get_CardDetail(cardId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_CardDetail().format(cardId))
    if len(rows) == 0:
        return None
    Output = dict()
    Output["Front"] = rows[0][0]
    Output["Back"] = rows[0][1]
    return Output


def update_CardBackByCardId(back: str, cardId: str):
    back = checkedString(back)
    # print("--- BACK ---")
    # print(back)
    # print("--- END BACK ---")
    gateway = GetGateway()
    RunQuery(gateway.entry_point.update_CardBackByCardId().format(back, cardId))
    print("--- UPDATE SUCCESSFULLY ---")


def get_CardTagByCardId(cardId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_CardTagByCardId().format(cardId))
    Tags = [i[0] for i in rows]
    if len(Tags) == 0:
        Tags = ["no special tag"]
    return Tags


def get_DeckTagByDeckId(deckId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_DeckTagByDeckId().format(deckId))
    Tags = [i[0] for i in rows]
    if len(Tags) == 0:
        Tags = ["no special tag"]
    return Tags


def get_BackCardPrompt(tag: str, front: str, responseLanguage: str):
    gateway = GetGateway()
    try:
        return gateway.entry_point.get_BackCardPrompt(tag, front, responseLanguage)
    except:
        return None


def get_BackCardAIGenerated(tag: str, front: str, responseLanguage: str):
    try:
        Prompt = get_BackCardPrompt(tag, front, responseLanguage)
    except:
        return ""
    back = FreePrompt(Prompt)
    return back


def get_CardIdBackNull(deckId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_CardIdBackNull().format(deckId))
    return list(i[0] for i in rows)


def get_CardFrontByCardId(cardId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_CardFrontByCardId().format(cardId))
    return rows[0][0]


def get_CardIdHavingReviewDateLessThanNowByDeckId(deckId: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.get_CardIdHavingReviewDateLessThanNowByDeckId().format(
            deckId
        )
    )
    return list(i[0] for i in rows)


def get_CardIdNotHavingReviewDateByDeckId(deckId: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.get_CardIdNotHavingReviewDateByDeckId().format(deckId)
    )
    return list(i[0] for i in rows)


def get_CardIdHavingReviewDateGreaterThanNowByDeckId(deckId: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.get_CardIdHavingReviewDateGreaterThanNowByDeckId().format(
            deckId
        )
    )
    return list(i[0] for i in rows)


def get_CustomDeckInfoByDeckIdAndUserId(deckId: str, userId: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.get_CustomDeckInfoByDeckIdAndUserId().format(deckId, userId)
    )
    Data = {"learning_pace": 5, "card_per_day": 5}
    if len(rows) > 0:
        Data["learning_pace"] = int(rows[0][0])
        Data["card_per_day"] = int(rows[0][1])
    else:
        insert_CustomDeckInfoByDeckIdAndUserId(deckId, userId, 5, 5)
    return Data


def update_CustomDeckInfoByDeckIdAndUserId(
    deckId: str, userId: str, learning_pace: int, card_per_day: int
):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.update_CustomDeckInfoByDeckIdAndUserId().format(
            learning_pace, card_per_day, deckId, userId
        )
    )


def insert_CustomDeckInfoByDeckIdAndUserId(
    deckId: str, userId: str, learning_pace: int, card_per_day: int
):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.insert_CustomDeckInfoByDeckIdAndUserId().format(
            learning_pace, card_per_day, deckId, userId
        )
    )


def get_UserDetailById(userId: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.get_UserDetailById().format(userId))
    Data = dict()
    Data["accountType"] = rows[0][0]
    Data["theme"] = rows[0][1]
    Data["userName"] = rows[0][2]
    return Data


def update_UserDetailById(userId: str, theme: str, useName: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.update_UserDetailById().format(
            toString(theme), toString(useName), userId
        )
    )


def get_ConfidentByCardIdAndUserId(cardId: str, userId: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.get_ConfidentByCardIdAndUserId().format(cardId, userId)
    )
    if len(rows) == 0:
        return None
    return int(rows[0][0])


def insert_LearningProgress(cardId: str, userId: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.insert_LearningProgress().format(cardId, userId)
    )


def update_LearningProgress(cardId: str, userId: str, confident: int):
    Step = (
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        14,
        15,
        16,
        18,
        19,
        21,
        22,
        24,
        26,
        27,
        29,
        31,
        33,
        35,
        37,
        39,
        41,
        44,
        46,
        48,
        51,
        54,
        56,
        59,
        62,
        65,
        68,
        71,
        75,
        78,
        82,
        85,
        89,
        93,
        97,
        102,
        106,
        111,
        115,
        120,
        125,
        130,
        136,
        141,
        147,
        153,
        160,
        166,
        173,
        180,
        187,
        194,
        202,
        210,
        218,
        226,
        235,
        244,
        254,
        263,
        273,
        284,
        295,
        306,
        317,
        329,
        342,
        354,
        368,
        381,
        396,
        410,
        426,
        441,
        458,
        475,
        492,
        510,
        529,
        548,
        568,
        589,
        611,
        633,
        656,
        680,
        704,
        730,
    )
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.update_LearningProgress().format(
            confident, "review_date+{}".format(Step[confident]), cardId, userId
        )
    )


def insert_Deck(authorId: str, deckName: str, isPublic: str, backLang: str):
    gateway = GetGateway()
    if isPublic == "public":
        isPublic = "true"
    else:
        isPublic = "false"
    rows = RunQuery(
        gateway.entry_point.insert_Deck().format(
            authorId, toString(deckName), isPublic, toString(backLang)
        )
    )


def update_DeckByDeckId(deckId: str, deckName: str, isPublic: str, backLang: str):
    gateway = GetGateway()
    if isPublic == "public":
        isPublic = "true"
    else:
        isPublic = "false"
    rows = RunQuery(
        gateway.entry_point.update_Deck().format(
            toString(deckName), isPublic, toString(backLang), deckId
        )
    )


def update_DeckSetting(deckId: str, userId: str, learning_pace: str, card_per_day: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.update_DeckSetting().format(
            learning_pace, card_per_day, deckId, userId
        )
    )


def delete_DeckTag(deckId: str, tag: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.delete_DeckTag().format(deckId, toString(tag)))


def insert_DeckTag(deckId: str, tag: str):
    gateway = GetGateway()
    rows = RunQuery(gateway.entry_point.insert_DeckTag().format(deckId, toString(tag)))


def insert_Card(deckId: str, Front: str, Back: str):
    gateway = GetGateway()
    rows = RunQuery(
        gateway.entry_point.insert_Card().format(
            deckId, toString(Front), toString(Back)
        )
    )


# ---------------------------------------------------------------------------------
# Fake_UserEmailPass = {"user1": "pass1", "user2": "pass2"}
# Fake_UserEmailID = {"user1": "1", "user2": "2"}
# Fake_DeckIdList = {"1": ["1D0", "1D1", "1D2"]}  # , "2": ["2D0", "2D1"]}
# Fake_CardIdList = {"1D0": ["1D0C0", "1D0C1", "1D0C2"], "1D1": ["1D1C0", "1D1C1"]}
# Fake_DeckDetail = {
#     "1D0": {"BackLanguage": "English", "Title": "N5 vocab", "ViewType": "public"},
#     "1D1": {"BackLanguage": "English", "Title": "N4 vocab", "ViewType": "public"},
#     "1D2": {"BackLanguage": "English", "Title": "N3 vocab", "ViewType": "private"},
# }
# Fake_CardDetail = {
#     "1D0C0": {
#         "Front": """青い, 蒼い, 碧い, あおい""",
#         "Back": """1. blue, azure
# mostly archaic or in ref. to fruits, vegetables and traffic lights
# 2. green
# Only 青い, Only 蒼い
# 3. pale (facial color), gray, grey
# 4. unripe, inexperienced""",
#     },
#     "1D0C1": {
#         "Front": """明るい, あかるい""",
#         "Back": """1. light, bright, well-lit, well-lighted
# 2. bright (colour)
# 3. cheerful, spirited, sunny (e.g. disposition)
# 4. bright (future, prospects, etc.), rosy, encouraging, promising
# as ...に明るい
# 5. knowledgeable (about), familiar (with), well versed (in), well acquainted (with)""",
#     },
#     "1D0C2": {
#         "Front": """厚い, 篤い, あつい""",
#         "Back": """1. thick, deep, heavy
# 2. kind, cordial, hospitable, warm, faithful
# Only 篤い
# 3. serious (of an illness)
# 4. abundant""",
#     },
#     "1D1C0": {
#         "Front": """謝る, あやまる""",
#         "Back": """Godan verb, Transitive
# 1. to apologize, to apologise
# dated term
# 2. to refuse, to decline
# Intransitive
# 3. to be unable to bear, to be defeated (by), to be at a loss""",
#     },
#     "1D1C1": {
#         "Front": """以上, 已上, いじょう""",
#         "Back": """Noun, used as a suffix, Antonym: 以下・1
# 1. not less than ..., ... and over, ... and above, ... and upwards, ... or more
# 2. beyond (e.g. one's expectations), more than, further than
# May take 'no'
# 3. the above, the above-mentioned, the aforementioned, the foregoing
# Conjunction, after a verb
# 4. since ..., seeing that ..., now that ..., once ...
# Expression
# 5. that's all, that is the end""",
#     },
# }


# def get_UserPassword_Fake(email: str):
#     return Fake_UserEmailPass.get(email, None)


# def get_UserId_Fake(email: str):
#     return Fake_UserEmailID.get(email, None)


# def get_DeckIdList_Fake(userId: str):
#     return Fake_DeckIdList.get(userId, None)


# def get_CardIdList_Fake(deckId: str):
#     return Fake_CardIdList.get(deckId, None)


# def get_DeckDetail_Fake(deckId: str):

#     return Fake_DeckDetail.get(deckId, None)


# def get_CardDetail_Fake(cardId: str):
#     return Fake_CardDetail.get(cardId, None)


# def set_UserAccount(email: str, password: str):
#     pass


# def set_DeckDetail(deckId: str, BackLanguage: str, Title: str, ViewType: str):
#     if deckId in Fake_DeckDetail:
#         Fake_DeckDetail[deckId]["BackLanguage"] = BackLanguage
#         Fake_DeckDetail[deckId]["Title"] = Title
#         Fake_DeckDetail[deckId]["ViewType"] = ViewType


# def set_CardDetail(cardID: str, Front: str, Back: str):
#     if cardID in Fake_CardDetail:
#         Fake_CardDetail[cardID]["Front"] = Front
#         Fake_CardDetail[cardID]["Back"] = Back


# -----------------------------------------------------------------------------------------------
