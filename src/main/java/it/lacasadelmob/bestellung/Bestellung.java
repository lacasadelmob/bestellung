package it.lacasadelmob.bestellung;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class Bestellung {
  private final String id;
  private final String name;
  private final boolean salami;
  private final boolean schinken;
  private final boolean pilze;
  private final boolean artischocken;
  private final boolean oliven;
  private final boolean zwiebeln;
  private ZonedDateTime abholzeit;
  private boolean abgeholt;

  public Bestellung(
      String name,
      boolean salami,
      boolean schinken,
      boolean pilze,
      boolean artischocken,
      boolean oliven,
      boolean zwiebeln) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.salami = salami;
    this.schinken = schinken;
    this.pilze = pilze;
    this.artischocken = artischocken;
    this.oliven = oliven;
    this.zwiebeln = zwiebeln;
    this.abholzeit = null;
    this.abgeholt = false;
  }

  public String getPizza() {
    var zutaten =
        Map.of(
                "Salami",
                salami,
                "Schinken",
                schinken,
                "Pilze",
                pilze,
                "Artischocken",
                artischocken,
                "Oliven",
                oliven,
                "Zwiebeln",
                zwiebeln)
            .entrySet()
            .stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .collect(Collectors.joining(", "));
    if (zutaten.isEmpty()) {
      return "Pizza mit Tomatensoße, Mozzarella";
    }
    return "Pizza mit Tomatensoße, Mozzarella, " + zutaten;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ZonedDateTime getAbholzeit() {
    return abholzeit;
  }

  public boolean getAbgeholt() {
    return abgeholt;
  }

  public void setAbholzeit(ZonedDateTime abholzeit) {
    this.abholzeit = abholzeit;
  }

  public void setAbgeholt(boolean abgeholt) {
    this.abgeholt = abgeholt;
  }
}
