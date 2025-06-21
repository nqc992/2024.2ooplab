import psycopg2
import ProtectedData


# def test_db():
#     DB_info = ProtectedData.getDatabaseKey()
#     conn = psycopg2.connect(
#         database=DB_info["DB_NAME"],
#         password=DB_info["DB_PASS"],
#         user=DB_info["DB_USER"],
#         host=DB_info["DB_HOST"],
#         port=DB_info["DB_PORT"],
#     )
#     print("Connected successfully")
#     cur = conn.cursor()
#     cur.execute("SELECT * FROM card")
#     rows = cur.fetchall()
#     for data in rows:
#         print(*data)
#     conn.close()


# # test_db()
# # ---------------------------------------------------
# from py4j.java_gateway import JavaGateway, GatewayParameters

# gateway = JavaGateway(gateway_parameters=GatewayParameters(port=25333))
# A = "???"
# try:
#     A = gateway.entry_point.SayHello("Ma Ngoc Thang")
# except:
#     print("Error when connecting with database")
# print("-------")
# print(A)
# print("-------")


def RunQuery(query: str):
    DB_info = ProtectedData.getDatabaseKey()
    conn = psycopg2.connect(
        database=DB_info["DB_NAME"],
        password=DB_info["DB_PASS"],
        user=DB_info["DB_USER"],
        host=DB_info["DB_HOST"],
        port=DB_info["DB_PORT"],
    )
    # print("Connected successfully---")
    # print(query)
    # print("----------------------")
    query = """UPDATE card SET back='走る, はしる
Meaning: To run
JLPT Level: N5
Type: Vocabulary
Details:

Part of Speech: Verb (自動詞 – intransitive verb)
Kana Reading: はしる (hashiru)
Kanji: 走る

Usage: Primarily means "to run," but can also describe the movement of vehicles or other things moving quickly. It emphasizes the act of running or moving at speed.
Examples:
1. Human Running:

私は毎日公園を走ります。 (Watashi wa mainichi kouen o hashirimasu.) - I run in the park every day.
彼は速く走ることができる。 (Kare wa hayaku hashiru koto ga dekiru.) - He can run fast.
子供たちが庭を走っている。 (Kodomo-tachi ga niwa o hashitte iru.) - The children are running in the garden.
マラソンで走るのは大変です。 (Marason de hashiru no wa taihen desu.) - Running in a marathon is tough.
犬が私に向かって走ってきた。 (Inu ga watashi ni mukatte hashitte kita.) - The dog ran towards me.

2. Vehicles Running (Moving):

電車が線路を走る。 (Densha ga senro o hashiru.) - The train runs on the tracks.
車が道路を走っている。 (Kuruma ga douro o hashitte iru.) - The car is running (driving) on the road.
バスは時間通りに走っています。 (Basu wa jikan-doori ni hashitte imasu.) - The bus is running on time.
タクシーを走らせてください。 (Takushii o hashirasete kudasai.) - Please drive the taxi. (This is a slightly different use; causative. "make the taxi run".)
船が海を走る。 (Fune ga umi o hashiru.) - The ship sails (runs) on the sea.

3. Other Movements (Figurative/Abstract):

時間が走るように過ぎる。 (Jikan ga hashiru you ni sugiru.) - Time passes quickly (as if running).
情報がインターネットを走る。 (Jouhou ga intaanetto o hashiru.) - Information travels (runs) on the internet.
電流が回路を走る。 (Denryuu ga kairo o hashiru.) - Electric current flows (runs) through the circuit.
彼の名前が世界中に走った。(Kare no namae ga sekaijuu ni hashitta.) - His name spread (ran) throughout the world.
インクが滲んで紙の上を走った。(Inku ga nijinde kami no ue o hashitta.) - The ink bled and ran on the paper.

4. Common Expressions:

走り出す (hashiridasu): To start running, to take off running.  Example:  彼は突然走り出した。(Kare wa totsuzen hashiridashita.) - He suddenly started running.
走り回る (hashirimawaru): To run around. Example: 子供たちが公園で走り回っている。(Kodomo-tachi ga kouen de hashirimawatte iru.) - The children are running around in the park.
走り書き (hashirigaki): Scrawl, hasty writing. Example: 走り書きでメモを残した。(Hashirigaki de memo o nokoshita.) - I left a note in a scrawl.

Important Notes:

走る is an intransitive verb (自動詞). This means it doesn''t take a direct object.  You wouldn''t say "I run the ball."
The particle を (o) can be used after a place to indicate the path along which someone/something is running.  For example, 公園を走る (kouen o hashiru) - to run through the park.
Use the correct verb form depending on the context (e.g., present tense, past tense, continuous tense). 走ります (hashirimasu - polite present), 走った (hashitta - past tense), 走っている (hashitte iru - continuous tense).
'  WHERE card.card_id = 43"""
    cur = conn.cursor()
    stage = cur.execute(query)
    rows = cur.fetchall()
    try:
        rows = cur.fetchall()
        print(rows)
    except:
        rows = "-No results-"
        print("Stage:", stage)
        input()
    finally:
        conn.close()
        return rows


RunQuery("")
