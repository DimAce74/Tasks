package ru.dimace74.services;

import ru.dimace74.documents.Document;

public interface DocService <Document> {
    Document save (Document document);
    Document findById (Integer id);
}
