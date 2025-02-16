package id.andra.note_app.feature_note.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.andra.note_app.feature_note.presentation.notes.NotesScreen
import id.andra.note_app.feature_note.presentation.util.Screen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        modifier = Modifier.fillMaxWidth(),
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(route = Screen.NotesScreen.route) {
            NotesScreen()
        }
    }
}