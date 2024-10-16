package it.lacasadelmob.bestellung;

import jakarta.annotation.PostConstruct;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Seeder {

  private final BestellungRepository bestellungRepository;

  private final Environment environment;

  private static final Logger logger = LoggerFactory.getLogger(Seeder.class);

  public Seeder(BestellungRepository bestellungRepository, Environment environment) {
    this.bestellungRepository = bestellungRepository;
    this.environment = environment;
  }

  @PostConstruct
  public void seed() {
    var port = environment.getProperty("server.port");
    var host =
        environment.getProperty(
            "server.address", "localhost"); // Standard ist "localhost", falls nicht gesetzt
    var bestellungNikolas =
        new Bestellung(
            "f47ac10b-58cc-4372-a567-0e02b2c3d479",
            "Nikolas Hermann",
            true,
            false,
            false,
            false,
            true,
            true);
    bestellungNikolas.setAbholzeit(ZonedDateTime.now().plusMinutes(20));
    bestellungRepository.save(bestellungNikolas);
    logger.info("Nikolas Pizza Bestellung:\t\thttp://{}:{}/bestellung/{}", host, port, bestellungNikolas.getId());
    var bestellungJoshua = new Bestellung(
        "a1b2c3d4-e5f6-7890-ab12-cd34ef560123",
        "Joshua Töpfer",
        false,
        false,
        true,
        false,
        false,
        true);
    bestellungRepository.save(
        bestellungJoshua);
    logger.info("Nikolas Pizza Bestellung:\t\thttps://{}:{}/bestellung/{}", host, port, bestellungJoshua.getId());
  }
}
