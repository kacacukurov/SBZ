package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.uns.ac.ftn.SBZprojekat.service.PacijentService;

@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;
}
