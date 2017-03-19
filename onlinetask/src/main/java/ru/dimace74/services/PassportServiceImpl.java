package ru.dimace74.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dimace74.dao.PassportRepository;
import ru.dimace74.documents.Passport;

@Service
@Repository
@Transactional
public class PassportServiceImpl implements PassportService {
    private PassportRepository repository;

    @Autowired
    public PassportServiceImpl(PassportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Passport save(Passport passport) {
        return repository.save(passport);
    }

    @Override
    public Passport findById (Integer id) {
        return repository.findOne(id);
    }
}
