import { TestBed } from '@angular/core/testing';

import { TopicService } from '../../services/topic/topic.service';

describe('TopicService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TopicService = TestBed.get(TopicService);
    expect(service).toBeTruthy();
  });
});
