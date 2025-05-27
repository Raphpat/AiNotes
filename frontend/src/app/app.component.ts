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
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'AI Notes';
}
