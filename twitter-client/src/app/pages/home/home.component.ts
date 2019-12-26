import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
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
  private otherUserId;
  private messages = [];
  private options = [];
  private message: string = "";
  private searchElement: string = "";

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
    private router: Router,
    private route: ActivatedRoute,
    private socket: SocketService
  ) { }

  ngOnInit() {
    this.user.id = this.route.snapshot.paramMap.get('id') as unknown as number;
    this.client = this.socket.getClient();
    this.initAllSubscribtions()
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
  search() {
    this.client.subscribe(`/user/topic/user/search`, callback => {
      let dto = JSON.parse(callback.body);
      this.options = dto.result;
    });
    this.service.search(this.searchElement);
  }
  go(id) {
    console.log("[HOME] - Eyyyyyyyyy");
  }
  goToUser() {
    let ohterUser = this.otherUserId as unknown as number;
    this.router.navigateByUrl('/user/' + this.user.id + "/" + ohterUser);
  }
  initAllSubscribtions() {
    this.client.subscribe(`/topic/user/chatRequest/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      this.chatKey = dto.chatID
      this.subToChat();
    })

    this.client.subscribe(`/topic/user/request/follow/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      alert("You got a follow request from " + dto.username);
      console.log("you got a request!")
      this.service.sendAnswer(this.user.id, dto.userID, true);
    })

    this.client.subscribe(`/topic/user/${this.user.id}/followRequestAnswer`, callback => {
      let dto = JSON.parse(callback.body);
      console.log("yeeeeah you got friends now!")
      alert("You and " + dto.username + " are now friends");
    })
  }
}
