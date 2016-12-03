package co.edu.polijic.controllers;

import co.edu.polijic.domain.Call;
import co.edu.polijic.domain.Driver;
import co.edu.polijic.services.CallServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController(value = "Calls")
@RequestMapping(value = "/calls")
@CrossOrigin(origins = "*")
public class CallsController {
    private final CallServices callServices;

    @Autowired
    public CallsController(CallServices callServices) {
        this.callServices = callServices;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Call> getRoomsPaginate(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "skip", required = false) Integer skip) {
        Optional<Integer> limitOptional = Optional.ofNullable(limit);
        Optional<Integer> skipOptional = Optional.ofNullable(skip);
        return callServices.getCalls().toList().blockingGet();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Call createRandomCall(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "skip", required = false) Integer skip) {
        Optional<Integer> limitOptional = Optional.ofNullable(limit);
        Optional<Integer> skipOptional = Optional.ofNullable(skip);
        long LOWER_RANGE = 3000000000L; //assign lower range value
        long UPPER_RANGE = 3999999999L; //assign upper range value
        Random random = new Random();
        long randomValue = LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
        Call call = new Call(randomValue, new Date(Calendar.getInstance().getTime().getTime()));
        return callServices.createCall(call).blockingGet();
    }
}
