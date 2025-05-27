import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Note } from '../../models/note.model';
import { NoteService } from '../../services/note.service';

@Component({
  selector: 'app-note-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="note-list">
      <h2>Your Notes</h2>
      <div class="notes">
        @for (note of notes; track note.id) {
          <div class="note-card">
            <p>{{ note.content }}</p>
            <small>Created: {{ note.createdAt | date:'medium' }}</small>
          </div>
        }
      </div>
    </div>
  `,
  styles: [`
    .note-list {
      padding: 20px;
    }
    .notes {
      display: grid;
      gap: 16px;
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    }
    .note-card {
      background: #f5f5f5;
      padding: 16px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .note-card p {
      margin: 0 0 8px 0;
      word-break: break-word;
    }
    .note-card small {
      color: #666;
    }
  `]
})
export class NoteListComponent implements OnInit {
  notes: Note[] = [];

  constructor(private noteService: NoteService) {}

  ngOnInit() {
    this.loadNotes();
  }

  loadNotes() {
    this.noteService.getNotes().subscribe(notes => {
      this.notes = notes;
    });
  }
} 