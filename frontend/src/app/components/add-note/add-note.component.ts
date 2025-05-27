import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NoteService } from '../../services/note.service';
import { Note } from '../../models/note.model';

@Component({
  selector: 'app-add-note',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.scss']
})
export class AddNoteComponent {
  noteContent: string = '';

  constructor(private noteService: NoteService) {}

  onSubmit() {
    if (this.noteContent.trim()) {
      const note: Note = {
        text: this.noteContent.trim(),
        createdAt: new Date()
        
      };

      this.noteService.addNote(note).subscribe({
        next: () => {
          this.noteContent = '';
          // You might want to emit an event to refresh the note list
        },
        error: (error) => {
          console.error('Error adding note:', error);
        }
      });
    }
  }
} 