package com.example.notesapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.model.Note

class NotesViewModel(
    private val repository: NotesRepository
    ) : ViewModel() {

    val notes = repository.notes
    val searchQuery = repository.searchQuery

    fun addNote(title: String,content: String){

        repository.addNote(title,content)
    }

    fun updateNote(id:Int,title: String,content: String){
        repository.updateNote(id,title,content)
    }

    fun deleteNote(id:Int){
        repository.deleteNote(id)
    }

    fun updateSearchQuery(query: String){
        repository.updateSearchQuery(query)
    }

    fun getFilteredNotes() {
        repository.getFilteredNotes()
    }

    fun getNotesById(id: Int): Note? {
        return repository.getNotesById(id)
    }

}