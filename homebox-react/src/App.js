import Router from "./routes"
function getFaviconEl() {
    return document.getElementById("favicon");
  }
function App() {

    const favicon = getFaviconEl(); // Accessing favicon element
    favicon.href = "http://localhost:3000/favicon.ico";
    return <Router />
}

export default App
