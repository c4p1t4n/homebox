package school.sptech.server.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UsuarioUtil {

    public static String getToken() {
        String caracteres = "qwertyuiopasdfghjklçzxcvbnm1234567890".repeat(3);

        List<String> letras = Arrays.asList(caracteres.split(""));

        Collections.shuffle(letras);

        String token = letras.stream().reduce("", String::concat);

        return token.substring(0, 16);
    }

    // necessaria a verificação de se aquele token ja é o utilizado pelo usuario (chances baixas mas nunca se sabe)
}
