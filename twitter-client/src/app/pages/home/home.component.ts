import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { SocketService } from 'src/app/services/socket.service';
import { User } from 'src/app/models/user';
import { Client } from 'webstomp-client';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private client: Client;
  private chatKey: number = -1;
  private messages = [];
  private message: string = "";

  user: User = {
    id: 0,
    firstName: "",
    lastName: "",
    age: 0,
    gender: 2,
    biography: ""
  }

  constructor(
    private service: UserService,
    private route: ActivatedRoute,
    private socket: SocketService
  ) { }

  ngOnInit() {
    this.user.id = this.route.snapshot.paramMap.get('id') as unknown as number;
    this.client = this.socket.getClient();
    this.client.subscribe(`/topic/user/chatRequest/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      this.chatKey = dto.chatID
      this.subToChat();
    })
  }

  open() {
    this.client.subscribe(`/user/topic/user/${this.user.id}/chat`, callback => {
      let dto = JSON.parse(callback.body);
      this.chatKey = dto.chatID;
      this.user = dto.user;
      this.subToChat();
    })
    this.service.startChat(this.user.id, 223);
  }

  msg() {
    this.service.sendMessage(this.chatKey, this.user.id, this.message);
  }
  close() {

  }

  subToChat() {
    this.client.subscribe(`/topic/user/chat/${this.chatKey}`, callback => {
      let dto = JSON.parse(callback.body);
      let tempList = [];
      dto.messages.forEach(msg => {
        tempList.push(msg.content);
      });
      this.messages = tempList;
    })
  }

}
