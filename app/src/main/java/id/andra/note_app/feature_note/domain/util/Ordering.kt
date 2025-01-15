package id.andra.note_app.feature_note.domain.util

sealed class Ordering {
    data object Ascending : Ordering()
    data object Descending : Ordering()
}