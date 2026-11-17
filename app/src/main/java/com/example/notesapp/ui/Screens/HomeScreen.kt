package com.example.notesapp.ui.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.model.Note
import com.example.notesapp.viewModel.NotesViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    viewModel: NotesViewModel,
    onAddNote:() ->Unit,
    onNoteClick:(Int) ->Unit,
){

    val notes by viewModel.notes.collectAsState(
        initial = listOf<Note>(
            Note(id=0,title="UI Concept worth existing",content="Note1"),
            Note(id=1,title="Book Review : The Design of everyday Things by Don Norman",content="Note2"),
            Note(id=2,title="Anime produce by ufotable",content="Note3"),
            Note(id=3,title="Mangas planned to read",content="Note4"),
        )

    )

    val searchQuery by viewModel.searchQuery.collectAsState(
        initial = ""
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text ="Notes",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "SearchNotes"
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info"
                        )
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNote
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "AddNotes",
                )
            }
        }
    ) { padding ->

        if(notes.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Create your first note!",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }else{

            LazyColumn(
                modifier=Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = notes,
                    key = { it.id }
                ){note ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
                                onNoteClick(note.id)
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = note.title,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
