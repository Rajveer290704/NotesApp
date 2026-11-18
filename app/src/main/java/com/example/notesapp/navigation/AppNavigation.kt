package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.ui.Screens.AddNoteScreen
import com.example.notesapp.ui.Screens.EditNoteScreen
import com.example.notesapp.viewModel.NotesViewModel
import com.example.notesapp.ui.Screens.HomeScreen
import com.example.notesapp.ui.components.NoteCard

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // Create one shared repository and ViewModel
    val repository = remember {
        NotesRepository()
    }

    val viewModel = remember {
        NotesViewModel(repository)
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // Home Screen
        composable("home") {

            HomeScreen(
                viewModel = viewModel,

                onAddNote = {
                    navController.navigate("Add")
                },

                onNoteClick = { noteId ->

                    navController.navigate("note/$noteId")

                }
            )
        }

        // Note Detail Screen
        composable(
            route = "note/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val noteId = backStackEntry.arguments
                ?.getInt("noteId") ?: -1

            val selectedNote = viewModel.getNotesById(noteId)

            if (selectedNote != null) {

                NoteCard(
                    note = selectedNote,
                    onClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {noteId ->
                        navController.navigate("Edit/$noteId")
                    }
                )
            }
        }

        composable(
            route = "Add"
        ) {
            AddNoteScreen(
                viewModel=viewModel,
                onBackClick ={
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "Edit/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val noteId = backStackEntry.arguments
                ?.getInt("noteId") ?: -1

            val selectedNote = viewModel.getNotesById(noteId)

            if (selectedNote != null) {

                EditNoteScreen(
                    note = selectedNote,
                    viewModel=viewModel,
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}