package id.andra.note_app.feature_note.data.local

import androidx.room.RoomDatabase

abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}