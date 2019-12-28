import { Injectable } from '@angular/core';
import { SocketService } from './socket.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private msg: any;

  constructor(private socket: SocketService) { }

  post(USER_ID: number, Content: string) {
    this.msg = {
      content: Content
    }
    this.socket.send(`/app/post/tweet/${USER_ID}`, this.msg);
    this.msg = null;
  }
}
