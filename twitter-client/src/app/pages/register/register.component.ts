import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Account } from 'src/app/models/account';
import { Client } from '@stomp/stompjs';
import { AccountService } from 'src/app/services/account.service';
import { Router } from '@angular/router';

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
  ) { }

  ngOnInit() {
    this.client = JSON.parse(localStorage.getItem('client'));
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
    var sub = this.client.subscribe('/topic/account', callback => {
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
