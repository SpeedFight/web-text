import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../services/topic/topic.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  topics: Topic[];

  constructor(private topicService: TopicService) { }

  ngOnInit() {
    this.topicService.getAll().subscribe(data => {
      this.topics = data;
    })
  }

}

export interface Topic{
  id:number;
  timestamp:Date;
  description:string;
}
