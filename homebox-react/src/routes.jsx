import { BrowserRouter, Route, Routes } from "react-router-dom"
import Register from "./pages/register"
import Login from "./pages/login"
import Home from "./pages/home"
import SearchResult from "./pages/searchResult"
import NotFound from "./pages/notFound"
import TestUploadFile from "./pages/testUploadFile"

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
                <Route
                    path="/testUpload"
                    element={<TestUploadFile />}
                    caseSensitive="false"
                />
                {<Route
                    path="*"
                    element={< NotFound />}
                    caseSensitive="false"
                />}
            </Routes>
        </BrowserRouter>
    )
}

export default Router
