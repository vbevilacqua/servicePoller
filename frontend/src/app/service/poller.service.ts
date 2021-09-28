import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Poller } from '../model/poller';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PollerService {

  private pollerUrl: string;

  constructor(private http: HttpClient) {
    this.pollerUrl = 'http://localhost:8080/url';
  }

  public findAll(): Observable<Poller[]> {
    return this.http.get<Poller[]>(this.pollerUrl);
  }

  public save(poller: Poller) {
    return this.http.post<Poller>(this.pollerUrl, poller);
  }

  public refreshStatus = (id: number) => {
      const url = `${this.pollerUrl}?id=${id}`;
      return this.http.put<Poller>(url, id);
  }
}
