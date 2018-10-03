import { Component, OnInit } from '@angular/core';

import { TopicService } from '../../services/topic/topic.service';
import { Topic } from '../../classes/topic'

@Component({
  selector: 'app-topic-form',
  templateUrl: './topic-form.component.html',
  styleUrls: ['./topic-form.component.css']
})
export class TopicFormComponent implements OnInit {
  
  topic:Topic;

  constructor(private topicService: TopicService) { }

  ngOnInit() {
  }

  public addTopic(){
    this.topicService.add(this.topic).subscribe(
      data => {alert("Succesfully Added topic")},
      Error => {alert("failed while adding topic")}
      )
  }

}
