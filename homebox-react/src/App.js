import Router from "./routes"

function getFaviconEl() {
    return document.getElementById("favicon");
  }
function App() {
    const favicon = getFaviconEl(); // Accessing favicon element
    favicon.href = "https://s3.amazonaws.com/homebox.com/assets/img/favicon.ico";
    return <Router />
}

export default App
