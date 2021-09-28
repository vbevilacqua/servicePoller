import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';

import { PollerListComponent } from './poller-list/poller-list.component';
import { PollerFormComponent } from './poller-form/poller-form.component';
import { PollerService } from './service/poller.service';

@NgModule({
  declarations: [
    AppComponent,
    PollerListComponent,
    PollerFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PollerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
