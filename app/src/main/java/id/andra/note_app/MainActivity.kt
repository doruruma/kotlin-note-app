package id.andra.note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import id.andra.note_app.feature_note.presentation.Navigation
import id.andra.note_app.ui.theme.NoteappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteappTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigation(navController = rememberNavController())
                }
            }
        }
    }
}