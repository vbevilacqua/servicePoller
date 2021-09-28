import { Component, OnInit } from '@angular/core';
import { Poller } from '../model/poller';
import { PollerService } from '../service/poller.service';

@Component({
  selector: 'app-poller-list',
  templateUrl: './poller-list.component.html',
  styleUrls: ['./poller-list.component.css']
})
export class PollerListComponent implements OnInit {
  errorMessage: undefined;
  pollers: Poller[];

  constructor(private pollerService: PollerService) {
  }

  ngOnInit() {
    this.pollerService.findAll().subscribe(data => {
      this.pollers = data;
      console.log(data);
    });
  }

}
