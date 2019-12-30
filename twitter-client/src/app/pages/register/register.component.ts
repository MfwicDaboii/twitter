import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Account } from 'src/app/models/account';
import { Client } from 'webstomp-client';
import { AccountService } from 'src/app/services/account.service';
import { Router } from '@angular/router';
import { SocketService } from 'src/app/services/socket.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  private client: Client;
  account: Account = {
    username: "",
    password: ""
  }
  user: User = {
    id: 0,
    firstName: "",
    lastName: "",
    age: 0,
    gender: 2,
    biography: ""
  }

  constructor(
    private service: AccountService,
    private router: Router,
    private socket: SocketService
  ) { }

  ngOnInit() {
    this.client = this.socket.getClient();
  }

  register() {
    console.log("[LOGIN] - Attempting to login!")
    var sub = this.client.subscribe('/topic/account', callback => {
      let msg = JSON.parse(callback.body)
      if (msg.state) {
        sub.unsubscribe();
        this.router.navigateByUrl('/home/' + msg.key);
      } else {
        console.log("[LOGIN] - Failure!")
      }
    });

    this.service.register(this.account, this.user);
  }

  cheat() {
    this.client = this.socket.getClient();
    console.log("Client: " + this.client)
    var sub = this.client.subscribe('/topic/account', callback => {
      console.log("[REGISTER] - inside cheat")
      let msg = JSON.parse(callback.body)
      if (msg.state) {
        sub.unsubscribe();
        this.router.navigateByUrl('/home/' + msg.key);
      } else {
        console.log("[LOGIN] - Failure!")
      }
    });

    this.service.cheat(this.account);
  }

}
