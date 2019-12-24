import { Injectable } from '@angular/core';
import { Client, over, Message } from "webstomp-client";
import * as SockJS from 'sockjs-client';
import { BehaviorSubject, Observable } from 'rxjs';
import { ClientState } from '../enums/clientState';
import { filter, first, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SocketService {
  private url: string = 'http://localhost:8090/ws';
  private client: any;
  private state: BehaviorSubject<ClientState>

  constructor() {
  }

  ngOnDestroy() {
    this.reconnect().pipe(first()).subscribe(client => client.disconnect(null));
  }

  connect() {
    this.state = new BehaviorSubject<ClientState>(ClientState.ATTEMPTING);
    let socket = new SockJS(this.url)
    this.client = over(socket);
    this.client.connect({},
      frame => {
        console.log("[SERVICE] - Frame: " + frame);
        this.state.next(ClientState.CONNECTED);
      },
      this.error);
  }

  reconnect(): Observable<Client> {
    return new Observable<Client>(observer => {
      this.state.pipe(filter(state => state === ClientState.CONNECTED)).subscribe(() => {
        console.log("[Socket - Service] - Got client")
        observer.next(this.client);
      })
    })
  }

  error(error) {
    console.log("[SERVICE] - errorCallBack -> " + error)
    setTimeout(() => {
      this.reconnect();
    }, 5000);
  }

  send(destination: string, msg) {
    console.log("[SERVICE] - Sending message to " + destination);
    this.client.send(destination, JSON.stringify(msg), {})
  }

  getClient(): Client {
    return this.client;
  }
}
