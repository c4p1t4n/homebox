package school.sptech.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.server.model.Category;
import school.sptech.server.model.Service;
import school.sptech.server.model.User;
import school.sptech.server.repository.CategoryRepository;
import school.sptech.server.repository.ServiceRepository;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.request.ServiceCreationRequest;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository dbRepositoryService;
    @Autowired
    private UserRepository dbRepositoryUser;
    @Autowired
    private CategoryRepository dbRepositoryCategory;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Service>> getAll() {
        List<Service> services = dbRepositoryService.findAll();

        return services.isEmpty() ? status(204).build() : status(200).body(services);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ServiceCreationRequest serviceBody) {
        Optional<Category> category = dbRepositoryCategory.findById(serviceBody.getFkCategory());
        Optional<User> worker = dbRepositoryUser.findById(serviceBody.getFkUser());

        if (!category.isPresent()) {
            return status(400).body("Categoria inexistente");
        }
        if (!worker.isPresent()) {
            return status(400).body("Prestador de serviço inexistente");
        }

        if (!dbRepositoryService.findByNameAndWorkerId(serviceBody.getName(), serviceBody.getFkUser()).isEmpty()) {
            return status(400).body("Serviço de mesmo nome ja cadastrado");
        }

        Service service = new Service(worker.get(), category.get(), serviceBody.getName(), serviceBody.getDescription(),
                serviceBody.getReferencePrice());

        service = dbRepositoryService.save(service);

        return status(201).body(service);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Service> getId(@PathVariable Integer id) {
        Optional<Service> service = dbRepositoryService.findById(id);

        return service.isPresent() ? status(204).build()
                : status(200).body(service.get());
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getServicesOfWorker/{idWorker}")
    public ResponseEntity<List<Service>> getServicesOfWorker(@PathVariable Integer idWorker) {
        List<Service> listServices = dbRepositoryService.findByWorkerId(idWorker);

        if (listServices.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listServices);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        if (!dbRepositoryService.existsById(id)) {
            return status(404).build();
        }

        dbRepositoryService.deleteById(id);
        return status(200).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Service> putMethodName(@PathVariable Integer id, @RequestBody Service newService) {
        if (Objects.nonNull(newService.getIdService()) && id != newService.getIdService()) {
            return status(400).build();
        } else if (Objects.isNull(newService.getIdService())) {
            newService.setIdService(id);
        }

        Service service = dbRepositoryService.save(newService);

        return status(200).body(service);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/name/{id}/{newName}")
    public ResponseEntity<Service> patchName(@PathVariable Integer id, @PathVariable String newName) {

        Optional<Service> serviceOptional = dbRepositoryService.findById(id);

        if (!serviceOptional.isPresent()) {
            return status(404).build();
        }
        Service service = serviceOptional.get();

        service.setName(newName);

        service = dbRepositoryService.save(service);

        return status(200).body(service);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/description/{id}/{newDescription}")
    public ResponseEntity<Service> patchDescription(@PathVariable Integer id, @PathVariable String newDescription) {

        Optional<Service> serviceOptional = dbRepositoryService.findById(id);

        if (!serviceOptional.isPresent()) {
            return status(404).build();
        }
        Service service = serviceOptional.get();

        service.setDescription(newDescription);

        service = dbRepositoryService.save(service);

        return status(200).body(service);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/name/{id}/{newReferencePrice}")
    public ResponseEntity<Service> patchReferencePrice(@PathVariable Integer id,
                                                       @PathVariable Double newReferencePrice) {

        Optional<Service> serviceOptional = dbRepositoryService.findById(id);

        if (!serviceOptional.isPresent()) {
            return status(404).build();
        }
        Service service = serviceOptional.get();

        service.setReferencePrice(newReferencePrice);

        service = dbRepositoryService.save(service);

        return status(200).body(service);
    }

}
