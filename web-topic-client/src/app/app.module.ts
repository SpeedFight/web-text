import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { TopicFormComponent } from './components/topic-form/topic-form.component';
import { NoteListComponent } from './components/note-list/note-list.component';
const appRoutes: Routes = [
  { path: 'topic/:id',      component: NoteListComponent },
  { path: '', component: TopicListComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  declarations: [
    AppComponent,
    TopicListComponent,
    TopicFormComponent,
    NoteListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
