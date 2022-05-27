import {BrowserRouter, Route, Routes} from "react-router-dom"
import Register from "./pages/register"
import Login from "./pages/login"
import Home from "./pages/home"
import SearchResult from "./pages/searchResult"

function Router() {
    return (
        <BrowserRouter>
            <Routes>
                <Route
                    path="/login"
                    element={<Login />}
                    caseSensitive="false"
                />
                <Route
                    path="/register"
                    element={<Register />}
                    caseSensitive="false"
                />
                <Route
                    path="/"
                    element={<Home />}
                    caseSensitive="false"
                />
                <Route
                    path="/search"
                    element={<SearchResult />}
                    caseSensitive="false"
                />
                {/* <Route
                    path="*"
                    element={<Page404 />}
                    caseSensitive="false"
                /> */}
            </Routes>
        </BrowserRouter>
    )
}

export default Router
