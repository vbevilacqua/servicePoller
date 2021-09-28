import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PollerListComponent } from './poller-list/poller-list.component';
import { PollerFormComponent } from './poller-form/poller-form.component';

const routes: Routes = [
  { path: 'urls', component: PollerListComponent },
  { path: 'addUrl', component: PollerFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
