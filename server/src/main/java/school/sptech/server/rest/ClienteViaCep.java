package school.sptech.server.rest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.server.response.ViaCepResponse;


@FeignClient(value = "viacep", url ="https://viacep.com.br/ws/")
public interface ClienteViaCep {
    @GetMapping("{cep}/json")
    ViaCepResponse getCep(@PathVariable String cep);
}
