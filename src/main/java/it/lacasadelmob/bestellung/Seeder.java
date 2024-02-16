package it.lacasadelmob.bestellung;

import jakarta.annotation.PostConstruct;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;

@Component
public class Seeder {

  private final BestellungRepository bestellungRepository;

  public Seeder(BestellungRepository bestellungRepository) {
    this.bestellungRepository = bestellungRepository;
  }

  @PostConstruct
  public void seed() {
    var bestellung = new Bestellung("Nikolas Hermann", true, false, false, false, true, true);
    bestellung.setAbholzeit(ZonedDateTime.now().plusMinutes(20));
    bestellungRepository.save(bestellung);
    bestellungRepository.save(
        new Bestellung("Joshua Töpfer", false, false, true, false, false, true));
  }
}
