from nicegui import app as nicegui_app, ui
from Pages.Deck_tab import Deck_tab
from Pages.Card_tab import Card_tab
from Pages.Ask_AI_tab import Ask_AI_tab
from Database_controller import update_UserDetailById


def logout() -> None:
    nicegui_app.storage.user.clear()
    ui.navigate.to("/login")


def ApplySetting(user_name, theme, user_email, user_password):
    ThemeDict = {1: "Light", 2: "Dark"}
    theme = ThemeDict[theme]
    if user_name == "":
        user_name = nicegui_app.storage.user.get("userName")
    update_UserDetailById(nicegui_app.storage.user.get("userId"), theme, user_name)
    print("Updated account setting")
    ui.notify("Updated account setting", type="positive")
    pass


def AccountSettingDialog():
    print("Running account setting")
    ui.notify("Change name and theme only", type="warning")
    with ui.dialog() as dialog, ui.card():
        ui.label("Account setting")
        user_name = ui.input(
            label="User name",
            # placeholder=nicegui_app.storage.user.get("userName"),
            value=nicegui_app.storage.user.get("userName"),
        )
        with ui.row():
            ui.label("Default theme")
            if nicegui_app.storage.user.get("theme") == "Light":
                theme_id = 1
            else:
                theme_id = 2
            theme = ui.select({1: "Light", 2: "Dark"}, value=theme_id)
        user_email = ui.input(
            label="User email",
            # placeholder=nicegui_app.storage.user.get("userEmail"),
            value=nicegui_app.storage.user.get("userEmail"),
        )
        user_password = ui.input(
            label="User password",
            # placeholder=nicegui_app.storage.user.get("password"),
            value=nicegui_app.storage.user.get("password"),
        )
        with ui.row():
            ui.button(
                "Apply",
                on_click=lambda: ApplySetting(
                    user_name.value, theme.value, user_email.value, user_password.value
                ),
            )
            ui.button("Cancel", on_click=dialog.close)
    dialog.open()
    print("end run account setting")


@ui.page("/home")
def Home_page():
    dark_mode = ui.dark_mode()
    if nicegui_app.storage.user.get("theme") == "Dark":
        dark_mode.enable()
    with ui.header().classes(replace="row items-center") as header:
        ui.button(on_click=lambda: left_drawer.toggle(), icon="menu").props(
            "flat color=white"
        )
        with ui.tabs() as tabs:
            ui.tab("My Decks")
            ui.tab("My Cards")
            ui.tab("Ask AI")

    with ui.footer(value=False) as footer:
        ui.label("Footer")

    with ui.left_drawer().classes(
        "border"
    ) as left_drawer:  # "bg-blue-100   .classes("100")"
        ui.menu_item(
            "Hello {}".format(nicegui_app.storage.user.get("userName")),
            on_click=lambda: AccountSettingDialog(),
        )
        ui.menu_item("Light Mode", on_click=dark_mode.disable)
        ui.menu_item("Dark Mode", on_click=dark_mode.enable)
        ui.menu_item(
            "Account type: {}".format(nicegui_app.storage.user.get("accountType"))
        )
        ui.menu_item(" Logout 𓉘➜", on_click=logout)

    # with ui.page_sticky(position="bottom-right", x_offset=20, y_offset=20):
    #     ui.button(on_click=footer.toggle, icon="contact_support").props("fab")

    # CurrentDeckId = [None]
    @ui.refreshable
    def Refreshable_Cardtab():
        Card_tab()

    @ui.refreshable
    def Refreshable_Decktab():
        Deck_tab(tabs, Refreshable_Cardtab, Refreshable_Decktab)

    with ui.tab_panels(tabs, value="My Decks").classes("w-full"):
        with ui.tab_panel("My Decks"):
            Refreshable_Decktab()
        with ui.tab_panel("My Cards"):
            Refreshable_Cardtab()
        with ui.tab_panel("Ask AI"):
            Ask_AI_tab()
