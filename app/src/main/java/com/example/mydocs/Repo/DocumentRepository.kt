package com.example.mydocs.Repo


import com.example.mydocs.Roomdatabse.DocumentDao
import com.example.mydocs.Roomdatabse.DocumentEntity
import kotlinx.coroutines.flow.Flow

class DocumentRepository(private val documentDao: DocumentDao) {

    val allDocuments: Flow<List<DocumentEntity>> = documentDao.getAllDocuments()

    suspend fun insertDocument(document: DocumentEntity) {
        documentDao.insertDocument(document)
    }

    suspend fun deleteDocument(document: DocumentEntity) {
        documentDao.deleteDocument(document)
    }
}