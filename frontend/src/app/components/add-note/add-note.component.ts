import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NoteService } from '../../services/note.service';
import { Note } from '../../models/note.model';

@Component({
  selector: 'app-add-note',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="add-note">
      <h2>Add New Note</h2>
      <form (ngSubmit)="onSubmit()" #noteForm="ngForm">
        <div class="form-group">
          <textarea
            [(ngModel)]="noteContent"
            name="content"
            placeholder="Write your note here..."
            required
            rows="4"
          ></textarea>
        </div>
        <button type="submit" [disabled]="!noteForm.form.valid">
          Save Note
        </button>
      </form>
    </div>
  `,
  styles: [`
    .add-note {
      padding: 20px;
      max-width: 600px;
      margin: 0 auto;
    }
    .form-group {
      margin-bottom: 16px;
    }
    textarea {
      width: 100%;
      padding: 12px;
      border: 1px solid #ddd;
      border-radius: 4px;
      resize: vertical;
      font-family: inherit;
    }
    button {
      background: #007bff;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 4px;
      cursor: pointer;
    }
    button:disabled {
      background: #ccc;
      cursor: not-allowed;
    }
    button:hover:not(:disabled) {
      background: #0056b3;
    }
  `]
})
export class AddNoteComponent {
  noteContent: string = '';

  constructor(private noteService: NoteService) {}

  onSubmit() {
    if (this.noteContent.trim()) {
      const note: Note = {
        content: this.noteContent.trim()
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