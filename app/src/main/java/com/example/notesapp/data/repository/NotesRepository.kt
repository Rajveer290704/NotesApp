package com.example.notesapp.data.repository

import com.example.notesapp.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NotesRepository {
    //Maintain List of Notes
    private val _notes = MutableStateFlow<List<Note>>(
        listOf(
            Note(id=0,title="UI Concept worth existing",content="Note1"),
            Note(id=1,title="Book Review : The Design of everyday Things by Don Norman",content="Note2"),
            Note(id=2,title="Anime produce by ufotable",content="Note3"),
            Note(id=3,title="Mangas planned to read",content="Note4"),
        )
    )
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()


    //Maintain Value of Search Block Value
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    //Add Notes
    fun addNote(title:String,content: String){


        _notes.update {currentNote ->
            val newId = (currentNote.maxOfOrNull { it.id } ?: -1) + 1
            val newNote = Note(id=newId,title=title, content = content)
            currentNote + newNote
        }
    }


    //Update Notes
    fun updateNote(id:Int,title:String,content:String){
        _notes.update { currentNote ->
            currentNote.map { note ->
                if(note.id == id){
                    note.copy(
                        title = title,
                        content = content
                    )
                }else{
                    note
                }
            }
        }
    }


    //Delete Notes
    fun deleteNote(id:Int){
        _notes.update { currentNote ->
            currentNote.filterNot { it.id == id }
        }
    }


    //Update value of search query
    fun updateSearchQuery(query:String){
        _searchQuery.value = query
    }


    //Give notes based on search query
    fun getFilteredNotes():List<Note>{
        val query = _searchQuery.value.trim()

        if(query.isEmpty()){
            return  _notes.value
        }
        return _notes.value.filter { note ->
            note.title.contains(query, ignoreCase = true) ||
                    note.content.contains(query, ignoreCase = true)
        }
    }


    //Give notes based on search id
    fun getNotesById(id:Int): Note?{
        return _notes.value.find { it.id ==id }
    }
}