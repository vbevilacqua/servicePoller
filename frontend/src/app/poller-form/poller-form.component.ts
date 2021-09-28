import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PollerService } from '../service/poller.service';
import { Poller } from '../model/poller';

@Component({
  selector: 'app-poller-form',
  templateUrl: './poller-form.component.html',
  styleUrls: ['./poller-form.component.css']
})
export class PollerFormComponent {
  errorMessage: undefined;
  success: boolean = false;
  poller: Poller;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private pollerService: PollerService) {
      this.poller = new Poller();
    }

  onSubmit() {
      this.pollerService.save(this.poller).subscribe(result => {
          this.gotoPollerList();
          this.success = result.alive;
        }, ({error}) => this.errorMessage = error.reason)
  }

  gotoPollerList() {
    this.router.navigate(['/poller']);
  }
}
