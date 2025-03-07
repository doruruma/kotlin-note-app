package id.andra.note_app.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.andra.note_app.ui.theme.BabyBlue
import id.andra.note_app.ui.theme.LightGreen
import id.andra.note_app.ui.theme.RedOrange
import id.andra.note_app.ui.theme.RedPink
import id.andra.note_app.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)