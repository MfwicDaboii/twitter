import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { SocketService } from './socket.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private msg: any;

  constructor(private socket: SocketService) { }

  startChat(user: User, USER_ID: number, USER2_ID: number) {
    this.socket.send(`/app/user/${USER_ID}/chat/request/${USER2_ID}`, user);
  }

  sendMessage(CHAT_ID: number, User: User, Content: string) {
    this.msg = {
      user: User,
      content: Content
    }
    this.socket.send(`/app/user/chat/message/${CHAT_ID}`, this.msg);
    this.msg = null;
  }

  closeChat(CHAT_ID: number) {
    this.msg = {
      follow: true
    }
    this.socket.send(`/app/user/chat/close/${CHAT_ID}`, this.msg);
    this.msg = null;
  }

  follow(USER_ID: number, FOLLOW_ID: number, isFollowing: boolean) {
    this.msg = {
      follow: isFollowing
    }
    this.socket.send(`/app/user${USER_ID}/follow/${FOLLOW_ID}`, this.msg);
    this.msg = null;
  }

  editProfile(USER_ID: number, user: User) {
    this.socket.send(`/app/user/${USER_ID}/update`, user)
  }
}
