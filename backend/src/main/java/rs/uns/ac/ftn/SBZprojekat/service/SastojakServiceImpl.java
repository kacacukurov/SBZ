package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.SastojakRepository;

@Service
public class SastojakServiceImpl implements SastojakService{

    @Autowired
    private SastojakRepository sastojakRepository;
}
