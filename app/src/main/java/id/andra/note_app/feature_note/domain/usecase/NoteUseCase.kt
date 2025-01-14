package id.andra.note_app.feature_note.domain.usecase

import id.andra.note_app.feature_note.domain.model.InvalidNoteException
import id.andra.note_app.feature_note.domain.model.Note
import id.andra.note_app.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteUseCase(
    private val repository: NoteRepository
) {

    fun getNotes(): Flow<List<Note>> {
        return repository.getNotes()
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