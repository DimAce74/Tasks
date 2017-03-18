package ru.dimace74.dao;

import org.springframework.data.repository.CrudRepository;
import ru.dimace74.documents.Document;

public interface Repository extends CrudRepository <Document, Integer> {
}
