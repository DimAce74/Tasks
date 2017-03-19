package ru.dimace74.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dimace74.dao.CertificateOfMarriageRepository;
import ru.dimace74.documents.CertificateOfMarriage;

@Service
@Repository
@Transactional
public class CertificateOfMarriageServiceImpl implements CertificateOfMarriageService {
    private CertificateOfMarriageRepository repository;

    @Autowired
    public CertificateOfMarriageServiceImpl(CertificateOfMarriageRepository repository) {
        this.repository = repository;
    }

    @Override
    public CertificateOfMarriage save(CertificateOfMarriage certificateOfMarriage) {
        return repository.save(certificateOfMarriage);
    }

    @Override
    public CertificateOfMarriage findById (Integer id) {
        return repository.findOne(id);
    }
}
