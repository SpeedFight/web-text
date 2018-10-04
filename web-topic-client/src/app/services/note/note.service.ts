import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import { Topic } from '../../classes/topic';
import { Note } from '../../classes/note';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  apiUrl:string = '//localhost:8080/api/topic';

  constructor(private http: HttpClient) { }

  get(id: number): Observable<Topic> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Topic>(url);
  }

  addNote(id: number, newNote: Note): Observable<Note> {
    const url = `${this.apiUrl}/${id}/note`;
    return this.http.post(url, newNote, httpOptions);
  }
}
