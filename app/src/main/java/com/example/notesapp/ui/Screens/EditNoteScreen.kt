package com.example.notesapp.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.model.Note
import com.example.notesapp.viewModel.NotesViewModel

@Composable
fun EditNoteScreen(
    note : Note,
    viewModel: NotesViewModel,
    onBackClick:() -> Unit
){

    var title by remember(note.id) {
        mutableStateOf(note.title)
    }

    var content by remember(note.id) {
        mutableStateOf(note.content)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "GoBack"
                )
            }
            IconButton(
                onClick = {
                    if (title.isNotBlank() &&
                        content.isNotBlank()
                    ) {

                        viewModel.updateNote(
                            id = note.id,
                            title = title.trim(),
                            content = content.trim()
                        )

                        onBackClick()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save"
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Title")
                    },
                    singleLine = false,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = content,
                    onValueChange = {
                        content = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Type Something")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }

}