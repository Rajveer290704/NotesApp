package com.example.notesapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
 fun NoteCard(
    note: Note,
    onClick :() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onClick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = SimpleDateFormat(
                    "dd MM YYYY, hh:mm a",
                    Locale.getDefault()
                ).format(Date(note.createdAt)),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}