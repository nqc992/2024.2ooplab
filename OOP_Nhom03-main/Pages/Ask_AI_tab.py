from nicegui import app as nicegui_app, ui, run
from LLM_API import FreePrompt


async def UpdateAI_Answer(AI_Answer, Prompt):
    AI_Answer.text = await run.io_bound(FreePrompt, Prompt)
    ui.notify("Received AI answer", type="positive")


def Ask_AI_tab():
    UserPrompt = ui.textarea(
        label="Ask AI something",
        placeholder="your prompt",
    ).classes("w-full")
    ui.button(
        "Ask AI",
        on_click=lambda: UpdateAI_Answer(AI_Answer, UserPrompt.value),
    )
    ui.separator()
    AI_Answer = ui.label("AI's answer").style(
        "white-space: pre-wrap;"
    )  # .classes("w-1/2 items-center")
