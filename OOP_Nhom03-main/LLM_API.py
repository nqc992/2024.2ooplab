from google import genai
from ProtectedData import getLLM_APIkey


# client = genai.Client(api_key=getLLM_APIkey())
# response = client.models.generate_content(
#     model="gemini-2.0-flash",
#     contents="""Show me example natural situations of different levels of saying sorry, from "gomen", "gomennasai", "sumimasen", etc. in japanese with clear explanation""",
# )
# print(response.text)
def FreePrompt(Prompt):
    client = genai.Client(api_key=getLLM_APIkey())
    print("--- Running Prompt ---")
    response = client.models.generate_content(
        model="gemini-2.0-flash",
        contents=Prompt,
    )
    print("--- Done prompt ---")
    return response.text


def CreateCardBack(Front, BackLanguage):
    client = genai.Client(api_key=getLLM_APIkey())
    Prompt = """""".format(Front, BackLanguage)
    response = client.models.generate_content(
        model="gemini-2.0-flash",
        contents=Prompt,
    )
    return response.text
