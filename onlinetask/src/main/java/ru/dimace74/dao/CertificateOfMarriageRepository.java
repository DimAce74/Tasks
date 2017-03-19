package ru.dimace74.dao;

import org.springframework.data.repository.CrudRepository;
import ru.dimace74.documents.CertificateOfMarriage;

public interface CertificateOfMarriageRepository extends CrudRepository<CertificateOfMarriage, Integer> {
}
