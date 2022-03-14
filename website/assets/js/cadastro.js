const btnChangeForm = document.querySelector("[changeForm]");
const cadastro = document.querySelector("[cadastro]");

changeForm.addEventListener("click", e => {
    document.getElementById("form_1").style.display = "none";
    document.getElementById("form_2").style.display = "";
});
cadastro.addEventListener("click", e => {
    window.alert("Cadastro realizado!");
});
