package it.lacasadelmob.bestellung;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
@RequestMapping({"", "/"})
public class BestellungController {

  private final BestellungRepository bestellungRepository;

  public BestellungController(BestellungRepository bestellungRepository) {
    this.bestellungRepository = bestellungRepository;
  }

  @GetMapping
  public String getForm() {
    return "form";
  }

  @PostMapping
  public String post(@ModelAttribute BestellungRequest bestellungRequest) {
    var id =
        bestellungRepository.save(
            new Bestellung(
                UUID.randomUUID().toString(), bestellungRequest.name(),
                bestellungRequest.salami() != null && bestellungRequest.salami(),
                bestellungRequest.schinken() != null && bestellungRequest.schinken(),
                bestellungRequest.pilze() != null && bestellungRequest.pilze(),
                bestellungRequest.artischocken() != null && bestellungRequest.artischocken(),
                bestellungRequest.oliven() != null && bestellungRequest.oliven(),
                bestellungRequest.zwiebeln() != null && bestellungRequest.zwiebeln()));
    return "redirect:/bestellung/" + id;
  }

  @GetMapping("/bestellung/{id}")
  public String getBestellung(@PathVariable String id, Model model) {
    var bestellung =
        bestellungRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    model.addAttribute("bestellung", bestellung);
    return "bestellung";
  }

  public record BestellungRequest(
      String name,
      Boolean salami,
      Boolean schinken,
      Boolean pilze,
      Boolean artischocken,
      Boolean oliven,
      Boolean zwiebeln) {}
}
