package id.andra.note_app.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.andra.note_app.feature_note.domain.util.NoteOrder
import id.andra.note_app.feature_note.domain.util.Ordering

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(Ordering.Descending),
    onOrderChange: (order: NoteOrder) -> Unit
) {
    Column(modifier = modifier) {

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOrder is NoteOrder.Title,
                onSelect = { onOrderChange.invoke(NoteOrder.Title(noteOrder.type)) },
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteOrder is NoteOrder.Date,
                onSelect = { onOrderChange.invoke(NoteOrder.Date(noteOrder.type)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteOrder is NoteOrder.Color,
                onSelect = { onOrderChange.invoke(NoteOrder.Color(noteOrder.type)) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOrder.type is Ordering.Ascending,
                onSelect = { onOrderChange.invoke(noteOrder.copy(Ordering.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteOrder.type is Ordering.Descending,
                onSelect = { onOrderChange.invoke(noteOrder.copy(Ordering.Descending)) }
            )
        }

    }
}