package co.edu.polijic.controllers;

import co.edu.polijic.domain.Driver;
import co.edu.polijic.domain.Ride;
import co.edu.polijic.services.DriverServices;
import co.edu.polijic.services.RideServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(value = "Drivers")
@RequestMapping(value = "/drivers")
@CrossOrigin(origins = "*")
public class DriverController {

    private final DriverServices driverServices;

    @Autowired
    public DriverController(DriverServices driverServices) {
        this.driverServices = driverServices;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Driver> getRoomsPaginate(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "skip", required = false) Integer skip) {
        Optional<Integer> limitOptional = Optional.ofNullable(limit);
        Optional<Integer> skipOptional = Optional.ofNullable(skip);
        return driverServices.getDrivers().toList().blockingGet();
    }
}
