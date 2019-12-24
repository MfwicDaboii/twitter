import { Injectable } from '@angular/core';
import { SocketService } from './socket.service';
import { Account } from '../models/account';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private key;

  constructor(
    public socket: SocketService) {
    this.socket.connect();
  }

  login(Username, Password) {
    let msg = {
      username: Username,
      password: Password
    };
    this.socket.send("/app/login", msg);
  }

  register(Account: Account, User: User) {
    let msg = {
      account: Account,
      user: User
    }
    console.log("[ACCOUNT SERVICE] - Content of the message: " + msg.account.username)
    this.socket.send("/app/register", msg);
  }

  cheat(account: Account) {

    let msg = {
      username: account.username,
      password: account.password,
    }
    console.log("[ACCOUNT SERVICE] - Content of the message: " + msg.username)
    console.log("[ACCOUNT SERVICE] - Content of the message: " + msg.password)
    this.socket.send("/app/login/hack", msg);
  }

  setKey(data) {
    this.key = data;
  }

}
