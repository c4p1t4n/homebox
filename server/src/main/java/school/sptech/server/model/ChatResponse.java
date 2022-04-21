package school.sptech.server.model;

import java.time.LocalDate;

public class ChatResponse {
    private String nome;
    private LocalDate date;
    private String msg;

    public ChatResponse(String nome, LocalDate date, String msg) {
        this.nome = nome;
        this.date = date;
        this.msg = msg;
    }

    public ChatResponse() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
