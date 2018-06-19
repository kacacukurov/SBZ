package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.LekRepository;

@Service
public class LekServiceImpl implements LekService{

    @Autowired
    private LekRepository lekRepository;
}
