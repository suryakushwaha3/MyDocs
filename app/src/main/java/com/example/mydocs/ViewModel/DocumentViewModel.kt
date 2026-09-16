package com.example.mydocs.ViewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydocs.Repo.DocumentRepository
import com.example.mydocs.Roomdatabse.AppDatabase
import com.example.mydocs.Roomdatabse.DocumentEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DocumentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DocumentRepository

    val allDocuments: StateFlow<List<DocumentEntity>>

    init {
        val documentDao = AppDatabase.getDatabase(application).documentDao()
        repository = DocumentRepository(documentDao)

        allDocuments = repository.allDocuments.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun addDocument(title: String, category: String, filePath: String) {
        viewModelScope.launch {
            val document = DocumentEntity(
                title = title,
                category = category,
                filePath = filePath
            )
            repository.insertDocument(document)
        }
    }

    fun deleteDocument(document: DocumentEntity) {
        viewModelScope.launch {
            repository.deleteDocument(document)
        }
    }
}