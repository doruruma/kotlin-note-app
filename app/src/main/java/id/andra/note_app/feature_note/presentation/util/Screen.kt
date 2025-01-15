package id.andra.note_app.feature_note.presentation.util

sealed class Screen(val route: String) {
    data object NotesScreen : Screen("notes_screen")
    data object NoteFormScreen : Screen("note_form_screen")
}