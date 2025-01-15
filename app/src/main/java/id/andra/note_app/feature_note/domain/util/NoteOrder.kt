package id.andra.note_app.feature_note.domain.util

sealed class NoteOrder(val type: Ordering) {
    class Title(type: Ordering) : NoteOrder(type)
    class Date(type: Ordering) : NoteOrder(type)
    class Color(type: Ordering) : NoteOrder(type)

    fun copy(type: Ordering): NoteOrder {
        return when (this) {
            is Title -> Title(type)
            is Date -> Date(type)
            is Color -> Color(type)
        }
    }
}