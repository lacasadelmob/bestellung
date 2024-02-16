package it.lacasadelmob.bestellung;

import static java.util.function.Predicate.not;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class BestellungRepository {
  private final ConcurrentHashMap<String, Bestellung> bestellungen = new ConcurrentHashMap<>();

  public String save(Bestellung bestellung) {
    bestellungen.put(bestellung.getId(), bestellung);

    return bestellung.getId();
  }

  public List<Bestellung> findOffeneBestellungen() {
    return bestellungen.values().stream().filter(not(Bestellung::getAbgeholt)).toList();
  }

  public Optional<Bestellung> findById(String id) {
    return Optional.ofNullable(bestellungen.get(id));
  }
}
