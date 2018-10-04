import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { NoteService } from '../../services/note/note.service';
import { Topic } from '../../classes/topic';
import { Note } from '../../classes/note';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})

export class NoteListComponent implements OnInit {

  topic: Topic = new Topic();
  note: Note = new Note();
  id: number;


  constructor(
    private noteService: NoteService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });

    this.refresh();
  }

  public refresh(){
    this.noteService.get(this.id).subscribe((data: Topic) => {
      this.topic = data;
    })
  }

  public addNote(author: string, text: string){
    this.note.author = author;
    this.note.text = text;
    this.noteService.addNote(this.id, this.note).subscribe(
      data => {alert("Succesfully added note")},
      Error => {alert("failed while adding note")}
      )
  }
  


}
