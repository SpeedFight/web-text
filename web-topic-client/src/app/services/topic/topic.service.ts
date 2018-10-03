import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

import { Topic } from '../../classes/topic'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};


@Injectable({
  providedIn: 'root'
})
export class TopicService {

  apiUrl:string = '//localhost:8080/api/topic';

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Topic[]>(this.apiUrl);
  }

  add(topic: Topic): Observable<Topic> {
    return this.http.post<Topic>(this.apiUrl, topic, httpOptions);
  }

  update(topic: Topic): Observable<Topic> {
    return this.http.put<Topic>(this.apiUrl, topic, httpOptions);
  }

  delete(id: number): Observable<{}> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete(url, httpOptions);
  }

}

