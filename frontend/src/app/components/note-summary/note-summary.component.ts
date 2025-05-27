import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NoteService } from '../../services/note.service';

@Component({
  selector: 'app-note-summary',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="summary-section">
      <h2>Notes Summary</h2>
      <button (click)="generateSummary()" [disabled]="isLoading">
        {{ isLoading ? 'Generating...' : 'Generate Summary' }}
      </button>
      @if (summary) {
        <div class="summary-content">
          <p>{{ summary }}</p>
        </div>
      }
    </div>
  `,
  styles: [`
    .summary-section {
      padding: 20px;
      max-width: 800px;
      margin: 0 auto;
    }
    button {
      background: #28a745;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 4px;
      cursor: pointer;
      margin-bottom: 16px;
    }
    button:disabled {
      background: #ccc;
      cursor: not-allowed;
    }
    button:hover:not(:disabled) {
      background: #218838;
    }
    .summary-content {
      background: #f8f9fa;
      padding: 16px;
      border-radius: 4px;
      border: 1px solid #dee2e6;
    }
    .summary-content p {
      margin: 0;
      line-height: 1.6;
    }
  `]
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