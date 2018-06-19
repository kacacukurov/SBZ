package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.PacijentRepository;

@Service
public class PacijentServiceImpl implements PacijentService{

    @Autowired
    private PacijentRepository pacijentRepository;
}
