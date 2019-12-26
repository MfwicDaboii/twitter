import { Component, OnInit } from '@angular/core';
import { Client } from 'webstomp-client';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { SocketService } from 'src/app/services/socket.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  private client: Client;
  private following: boolean = false;
  user: User = {
    id: 0,
    firstName: "",
    lastName: "",
    age: 0,
    gender: 2,
    biography: ""
  }
  private myId = -1;

  constructor(
    private service: UserService,
    private route: ActivatedRoute,
    private socket: SocketService
  ) { }

  ngOnInit() {
    this.user.id = this.route.snapshot.paramMap.get('usersid') as unknown as number;
    this.myId = this.route.snapshot.paramMap.get('id') as unknown as number;
    this.client = this.socket.getClient();
    this.client.subscribe(`/app/user/${this.myId}/get/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      this.user.firstName = dto.firstName;
      this.following = dto.isFollwing;
    });
  }

  follow() {
    if (this.following) {
      this.service.follow(this.myId, this.user.id, false);
    } else {
      this.service.follow(this.myId, this.user.id, true);
    }
  }

}
