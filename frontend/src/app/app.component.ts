import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AddNoteComponent } from './components/add-note/add-note.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { NoteSummaryComponent } from './components/note-summary/note-summary.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HttpClientModule,
    AddNoteComponent,
    NoteListComponent,
    NoteSummaryComponent
  ],
  template: `
    <div class="app-container">
      <header>
        <h1>AI Notes</h1>
      </header>
      <main>
        <app-add-note></app-add-note>
        <app-note-summary></app-note-summary>
        <app-note-list></app-note-list>
      </main>
    </div>
  `,
  styles: [`
    .app-container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }
    header {
      text-align: center;
      margin-bottom: 40px;
    }
    h1 {
      color: #333;
      font-size: 2.5rem;
      margin: 0;
    }
    main {
      display: flex;
      flex-direction: column;
      gap: 40px;
    }
  `]
})
export class AppComponent {
  title = 'AI Notes';
}
