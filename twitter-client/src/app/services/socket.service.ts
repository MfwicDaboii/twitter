import { Injectable } from '@angular/core';
import { Stomp, Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class SocketService {
  private url: string = 'http://localhost:8090/ws';
  private client: any;
  private key: string = "client";

  constructor() { }

  ngOnDestroy() {
    this.client.disconnect(null);
  }

  connect() {
    let socket = new SockJS(this.url)
    this.client = Stomp.over(socket);
    this.client.connect({}, frame => { console.log("[SERVICE] - Frame: " + frame); }, this.error);
    localStorage.setItem(this.key, JSON.stringify(this.client))
  }

  error(error) {
    console.log("[SERVICE] - errorCallBack -> " + error)
    setTimeout(() => {
      this.connect();
    }, 5000);
  }

  send(destination: string, msg) {
    console.log("[SERVICE] - Sending message to " + destination);
    this.client.send(destination, {}, JSON.stringify(msg))
  }
}
