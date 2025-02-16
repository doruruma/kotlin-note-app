package id.andra.note_app.feature_note.presentation.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.andra.note_app.feature_note.domain.model.Note
import id.andra.note_app.feature_note.domain.usecase.NoteUseCase
import id.andra.note_app.feature_note.domain.util.NoteOrder
import id.andra.note_app.feature_note.domain.util.Ordering
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCase: NoteUseCase
) : ViewModel() {

    private val _state: MutableState<NotesState> = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        getNotes(
            ordering = NoteOrder.Date(type = Ordering.Descending)
        )
    }

    private fun getNotes(ordering: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = useCase.getNotes(order = ordering).launchIn(viewModelScope)
    }

    private fun handleOrder(order: NoteOrder) {
        if (
            order::class == state.value.noteOrder::class &&
            order.type == state.value.noteOrder.type
        )
            return
        getNotes(ordering = order)
    }

    private fun handleDeleteNote(note: Note) = viewModelScope.launch {
        useCase.deleteNote(note)
        recentlyDeletedNote = note
    }

    private fun handleRestoreNote() = viewModelScope.launch {
        useCase.addNote(recentlyDeletedNote ?: return@launch)
    }

    private fun handleToggleOrderSection() {
        _state.value = state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> handleOrder(event.noteOrder)
            is NotesEvent.DeleteNote -> handleDeleteNote(event.note)
            NotesEvent.RestoreNote -> handleRestoreNote()
            NotesEvent.ToggleOrderSection -> handleToggleOrderSection()
        }
    }

}