import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { NoteService } from '../../services/note/note.service';
import { Topic } from '../../classes/topic';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})

export class NoteListComponent implements OnInit {

  topic: Topic = new Topic();
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
      console.log(this.id)
      console.log(this.topic)
    })
  }


}
