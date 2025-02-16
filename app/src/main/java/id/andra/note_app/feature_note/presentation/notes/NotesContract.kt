package id.andra.note_app.feature_note.presentation.notes

import id.andra.note_app.feature_note.domain.model.Note
import id.andra.note_app.feature_note.domain.util.NoteOrder
import id.andra.note_app.feature_note.domain.util.Ordering

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(Ordering.Descending),
    val isOrderSectionVisible: Boolean = false
)

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    data object RestoreNote : NotesEvent()
    data object ToggleOrderSection : NotesEvent()
}