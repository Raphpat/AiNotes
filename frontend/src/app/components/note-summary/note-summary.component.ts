import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NoteService } from '../../services/note.service';

@Component({
  selector: 'app-note-summary',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './note-summary.component.html',
  styleUrls: ['./note-summary.component.scss']
})
export class NoteSummaryComponent {
  summary: string | null = null;
  isLoading = false;

  constructor(private noteService: NoteService) {}

  generateSummary() {
    this.isLoading = true;
    this.noteService.getSummary().subscribe({
      next: (summary) => {
        this.summary = summary;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error generating summary:', error);
        this.isLoading = false;
      }
    });
  }
} 