import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account';
import { Client } from 'webstomp-client';
import { AccountService } from 'src/app/services/account.service';
import { Router } from '@angular/router';
import { SocketService } from 'src/app/services/socket.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private client: Client;
  private account: Account = {
    username: "",
    password: ""
  }

  constructor(
    private service: AccountService,
    private router: Router,
    private socket: SocketService
  ) { }

  ngOnInit() {
    this.client = this.socket.getClient();
  }

  login() {
    var sub = this.client.subscribe('/topic/account', callback => {
      let msg = JSON.parse(callback.body)
      if (msg.state) {
        sub.unsubscribe();
        this.router.navigateByUrl('/home/' + msg.key);
      } else {
        console.log("[LOGIN] - Failure!")
      }
    });
    this.service.login(this.account.username, this.account.password);
  }
  goto() {
    this.router.navigateByUrl('/register');
  }

}
