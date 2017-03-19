package ru.dimace74.dao;

import org.springframework.data.repository.CrudRepository;
import ru.dimace74.documents.Passport;

public interface PassportRepository extends CrudRepository<Passport, Integer> {
}
