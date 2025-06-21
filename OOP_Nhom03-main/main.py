# from fastapi import FastAPI
from nicegui import app as nicegui_app, ui, html
from Pages.Login_page import Login_page
from Pages.Home_page import Home_page

from fastapi import Request
from fastapi.responses import RedirectResponse
from starlette.middleware.base import BaseHTTPMiddleware
from typing import Optional

import psycopg2
import ProtectedData

# app = FastAPI()
unrestricted_page_routes = {"/login"}

import tracemalloc

tracemalloc.start(10000)


class AuthMiddleware(BaseHTTPMiddleware):
    """This middleware restricts access to all NiceGUI pages.

    It redirects the user to the login page if they are not authenticated.
    """

    async def dispatch(self, request: Request, call_next):
        if not nicegui_app.storage.user.get("authenticated", False):
            if (
                not request.url.path.startswith("/_nicegui")
                and request.url.path not in unrestricted_page_routes
            ):
                return RedirectResponse(f"/login?redirect_to={request.url.path}")
        return await call_next(request)


nicegui_app.add_middleware(AuthMiddleware)

# @app.get("/")
# def read_root():
#     return {"Hello": "World"}


@ui.page("/login")
async def login():
    Login_page()


@ui.page("/home")
async def home():
    Home_page()


# Integrate with your FastAPI Application
# ui.run_with(
#     app=app,
#     storage_secret="pick your private secret here",
# )
if __name__ in {"__main__", "__mp_main__"}:
    ui.run(storage_secret="THIS_NEEDS_TO_BE_CHANGED")
print("End run...")
