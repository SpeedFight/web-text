import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Topic} from '../../components/topic-list/topic-list.component'

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  constructor(private http: HttpClient) { }

  getAll() { 
    return this.http.get<Topic[]>('//localhost:8080/api/topic');
  }
}
