import { Component, OnInit } from '@angular/core';

import { TopicService } from '../../services/topic/topic.service';
import { Topic } from '../../classes/topic'

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  topics: Topic[];

  constructor(private topicService: TopicService) { }

  ngOnInit() {
    this.refresh();
    
  }

  public refresh(){
    this.topicService.getAll().subscribe((data: Topic[]) => {
      this.topics = data;
    })
  }

  public deleteTopic(id:number){
    this.topicService.delete(id).subscribe(
      data => {alert("Succesfully deleted topic")},
      Error => {alert("failed while deleting topic")}
      )
  }

}
