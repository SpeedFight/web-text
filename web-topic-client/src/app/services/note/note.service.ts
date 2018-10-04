import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { Topic } from '../../classes/topic';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  apiUrl:string = '//localhost:8080/api/topic';

  constructor(private http: HttpClient) { }

  get(id: number) {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Topic>(this.apiUrl);
  }
}
