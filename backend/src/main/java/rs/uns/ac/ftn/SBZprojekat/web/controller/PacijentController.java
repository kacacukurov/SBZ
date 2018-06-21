package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.uns.ac.ftn.SBZprojekat.service.DroolsService;
import rs.uns.ac.ftn.SBZprojekat.service.PacijentService;

@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private DroolsService droolsService;

    @RequestMapping(
            value = "/kaca",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity testDrools() {

        droolsService.pokreniDrools();

        return new ResponseEntity(HttpStatus.OK);
    }
}
