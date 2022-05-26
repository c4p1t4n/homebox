package school.sptech.server.controllers;

import static org.springframework.http.ResponseEntity.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.server.model.AccomplishedService;
import school.sptech.server.model.Rating;
import school.sptech.server.model.Scheduling;
import school.sptech.server.model.SchedulingStatus;
import school.sptech.server.model.Service;
import school.sptech.server.model.User;
import school.sptech.server.repository.AccomplishedServiceRepository;
import school.sptech.server.repository.RatingRepository;
import school.sptech.server.repository.SchedulingRepository;
import school.sptech.server.repository.SchedulingStatusRepository;
import school.sptech.server.repository.ServiceRepository;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.request.AccomplishServiceInfo;
import school.sptech.server.request.RatingCreationRequest;
import school.sptech.server.request.SchedulingCreationRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/schedulings")
public class SchedulingController {

    @Autowired
    private ServiceRepository dbRepositoryService;
    @Autowired
    private UserRepository dbRepositoryUser;
    @Autowired
    private SchedulingRepository dbRepositoryScheduling;
    @Autowired
    private SchedulingStatusRepository dbRepositorySchedulingStatus;
    @Autowired
    private AccomplishedServiceRepository dbRepositoryAccomplishedService;
    @Autowired
    private RatingRepository dbRepositoryRating;

    @GetMapping
    public ResponseEntity<List<Scheduling>> getAll() {
        List<Scheduling> schedulings = dbRepositoryScheduling.findAll();

        return schedulings.isEmpty() ? status(204).build() : status(200).body(schedulings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scheduling> getId(@PathVariable Integer id) {
        Optional<Scheduling> scheduling = dbRepositoryScheduling.findById(id);

        return scheduling.isPresent()
                ? status(204).build()
                : status(200).body(scheduling.get());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody SchedulingCreationRequest schedulingBody) {
        Optional<User> userOptional = dbRepositoryUser.findById(schedulingBody.getFkUser());
        Optional<Service> serviceOptional = dbRepositoryService.findById(schedulingBody.getFkService());
        if (!userOptional.isPresent()) {
            return status(404).body("Usuario inexistente");
        }
        if (!serviceOptional.isPresent()) {
            return status(404).body("Serviço inexistente");
        }
        User customer = userOptional.get();
        Service service = serviceOptional.get();

        if (customer.getType().equals("worker")) {
            return status(400).body("Usuário cadastrado como prestador de serviço");
        }

        if (dbRepositoryScheduling.existsByCustomerIdAndServiceIdService(customer.getId(),
                service.getIdService())) {
            return status(403).body("Usuário já possui agendamento do serviço em questão");
        }

        Scheduling scheduling = dbRepositoryScheduling.save(new Scheduling(customer, service));

        SchedulingStatus schedulingStatus = dbRepositorySchedulingStatus
                .save(new SchedulingStatus("scheduled", LocalDate.now(), scheduling));

        return status(201).body(schedulingStatus);
    }

    @PostMapping("/status/{id}/{status}")
    public ResponseEntity<Object> updateStatus(@PathVariable Integer id, @PathVariable String status) {
        Optional<Scheduling> schedulingOptional = dbRepositoryScheduling.findById(id);

        if (!schedulingOptional.isPresent()) {
            return status(404).body("Agendamento inexistente");
        }

        Scheduling scheduling = schedulingOptional.get();

        if (dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling(status,
                id)) {
            return status(400).body("Agendamento ja possui o status: " + status);
        }

        SchedulingStatus schedulingStatus = dbRepositorySchedulingStatus
                .save(new SchedulingStatus(status, LocalDate.now(), scheduling));

        return status(201).body(schedulingStatus);
    }

    @PostMapping("/accomplish/{id}")
    public ResponseEntity<Object> accomplishScheduling(@PathVariable Integer id,
            @RequestBody AccomplishServiceInfo serviceInfo) {
        // ! implementar refactor para fazer o prestador de serviço confirmar a execução
        // ! e apenas depois aparecer para o cliente (setar o status como done e na vez
        // ! (setar o status como done e na vez do cliente ver se ja é done)
        // ! ou associar o done com o prestador de serviço e o rated com o cliente (pois
        // ! mesmo se o cliente prefere não avaliar o status fica como rated para não
        // ! ficar floodando ele com esse pedido)
        Optional<Scheduling> schedulingOptional = dbRepositoryScheduling.findById(id);

        if (!schedulingOptional.isPresent()) {
            return status(404).body("Agendamento inexistente");
        }

        Scheduling scheduling = schedulingOptional.get();

        if (dbRepositoryAccomplishedService.existsById(scheduling.getIdScheduling())) {
            return status(400).body("O serviço relacionado a esse agendamento ja foi prestado");
        }

        LocalDate date = LocalDate.now();
        if (!dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("done",
                scheduling.getIdScheduling())) {
            dbRepositorySchedulingStatus.save(new SchedulingStatus("done", date,
                    scheduling));
        }

        AccomplishedService accomplishedService = dbRepositoryAccomplishedService.save(new AccomplishedService(
                scheduling, serviceInfo.getPrice(), serviceInfo.getDescription(), date));

        return status(201).body(accomplishedService);
    }

    @PostMapping("/rating/{id}")
    public ResponseEntity<Object> rateScheduling(@PathVariable Integer id,
            @RequestBody RatingCreationRequest ratingInfo) {
        Optional<Scheduling> schedulingOptional = dbRepositoryScheduling.findById(id);

        if (!schedulingOptional.isPresent()) {
            return status(404).body("Agendamento inexistente");
        }

        Scheduling scheduling = schedulingOptional.get();

        Optional<AccomplishedService> accomplishedService = dbRepositoryAccomplishedService
                .findById(scheduling.getIdScheduling());

        if (!accomplishedService.isPresent()) {
            return status(400).body("O serviço relacionado a esse agendamento ainda não foi prestado");
        }
        if (dbRepositoryRating.existsById(scheduling.getIdScheduling())) {
            return status(400).body("O serviço relacionado a esse agendamento já foi prestado e avaliado");
        }

        LocalDate date = LocalDate.now();
        if (!dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("rated",
                scheduling.getIdScheduling())) {
            dbRepositorySchedulingStatus.save(new SchedulingStatus("rated", date,
                    scheduling));
        }

        Rating rating = dbRepositoryRating
                .save(new Rating(accomplishedService.get(), ratingInfo.getRating(), ratingInfo.getDescription()));

        return status(201).body(rating);
    }

    @PostMapping("/skip-rating/{id}")
    public ResponseEntity<Object> skipRatingScheduling(@PathVariable Integer id) {
        Optional<Scheduling> schedulingOptional = dbRepositoryScheduling.findById(id);

        if (!schedulingOptional.isPresent()) {
            return status(404).body("Agendamento inexistente");
        }

        Scheduling scheduling = schedulingOptional.get();

        if (!dbRepositoryAccomplishedService.existsById(scheduling.getIdScheduling())) {
            return status(400).body("O serviço relacionado a esse agendamento ainda não foi prestado");
        }
        if (dbRepositoryRating.existsById(scheduling.getIdScheduling())) {
            return status(400).body("O serviço relacionado a esse agendamento já foi prestado e avaliado");
        }

        SchedulingStatus schedulingStatus = null;
        if (!dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("rated",
                scheduling.getIdScheduling())) {
            schedulingStatus = dbRepositorySchedulingStatus
                    .save(new SchedulingStatus("rated", LocalDate.now(), scheduling));
        } else {
            schedulingStatus = dbRepositorySchedulingStatus
                    .findFirstBySchedulingIdSchedulingOrderByStatusDateDesc(scheduling.getIdScheduling());
        }

        return status(201).body(schedulingStatus);
    }

}