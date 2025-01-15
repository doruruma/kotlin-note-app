package id.andra.note_app.feature_note.domain.usecase

import id.andra.note_app.feature_note.domain.model.InvalidNoteException
import id.andra.note_app.feature_note.domain.model.Note
import id.andra.note_app.feature_note.domain.repository.NoteRepository
import id.andra.note_app.feature_note.domain.util.NoteOrder
import id.andra.note_app.feature_note.domain.util.Ordering
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteUseCase(
    private val repository: NoteRepository
) {

    fun getNotes(order: NoteOrder = NoteOrder.Date(Ordering.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (order.type) {
                Ordering.Ascending -> {
                    when (order) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }

                Ordering.Descending -> {
                    when (order) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }

    suspend fun getNoteById(id: Int): Note? {
        return repository.getNoteById(id)
    }

    @Throws(InvalidNoteException::class)
    suspend fun addNote(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("Note title is required")
        if (note.content.isBlank())
            throw InvalidNoteException("Note content is required")
        repository.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

}