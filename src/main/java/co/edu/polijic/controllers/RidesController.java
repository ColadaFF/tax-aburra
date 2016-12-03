package co.edu.polijic.controllers;

import co.edu.polijic.domain.Ride;
import co.edu.polijic.services.RideServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@RestController(value = "Rides")
@RequestMapping(value = "/rides")
@CrossOrigin(origins = "*")
public class RidesController {

    private final RideServices rideServices;

    @Autowired
    public RidesController(RideServices rideServices) {
        this.rideServices = rideServices;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ride> getRoomsPaginate(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "skip", required = false) Integer skip) {
        Optional<Integer> limitOptional = Optional.ofNullable(limit);
        Optional<Integer> skipOptional = Optional.ofNullable(skip);
        return rideServices.listRides(1, 1).toList().blockingGet();
    }

    @RequestMapping(value = "/{rideId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride getRide(@PathVariable("rideId") Long rideId) {
        return rideServices.getRide(rideId).blockingGet();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride saveRide(@RequestBody Ride ride) {
        return rideServices.saveRide(ride).blockingGet();
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride updateRide(@RequestBody Ride ride) {
        return rideServices.updateRide(ride).blockingGet();
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ride deleteRide(@RequestBody Ride ride) {
        return rideServices.deleteRide(ride).blockingGet();
    }
}
