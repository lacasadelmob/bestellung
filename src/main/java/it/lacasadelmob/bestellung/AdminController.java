package it.lacasadelmob.bestellung;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping({"/admin"})
public class AdminController {

  public AdminController(BestellungRepository bestellungRepository) {
    this.bestellungRepository = bestellungRepository;
  }

  private final BestellungRepository bestellungRepository;

  @GetMapping
  public String getBestellungen(Model model) {
    var bestellungen = bestellungRepository.findOffeneBestellungen();
    model.addAttribute("bestellungen", bestellungen);
    return "bestellungen";
  }

  @PostMapping("/bestellung/{id}/dauer")
  public String postAbgeholt(@RequestParam Long dauer, @PathVariable String id) {
    var bestellung =
        bestellungRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    bestellung.setAbholzeit(ZonedDateTime.now(ZoneId.of("Europe/Berlin")).plusMinutes(dauer));

    return "redirect:/admin";
  }

  @PostMapping("/bestellung/{id}/abgeholt")
  public String postAbgeholt(@PathVariable String id) {
    var bestellung =
        bestellungRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    bestellung.setAbgeholt(true);

    return "redirect:/admin";
  }
}
