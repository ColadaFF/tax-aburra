package co.edu.polijic;

import co.edu.polijic.domain.Ride;
import co.edu.polijic.services.RideServices;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TaxAburraApplication {

    @Autowired
    private static RideServices rideServices;

    public static void main(String[] args) {
        SpringApplication.run(TaxAburraApplication.class, args);

    }
}
