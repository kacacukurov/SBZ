package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.PacijentAlergicanLekRepository;

@Service
public class PacijentAlergicanLekServiceImpl implements PacijentAlergicanLekService{

    @Autowired
    private PacijentAlergicanLekRepository pacijentAlergicanLekRepository;
}
