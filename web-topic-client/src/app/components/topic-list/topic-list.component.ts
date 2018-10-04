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
  topic:Topic = new Topic();

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

  public addTopic(description:Topic["description"]){
    this.topic.description = description;
    this.topicService.add(this.topic).subscribe(
      data => {alert("Succesfully Added topic")},
      Error => {alert("failed while adding topic")}
      )
  }

}
