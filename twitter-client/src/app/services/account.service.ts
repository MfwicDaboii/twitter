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
    private socket: SocketService
  ) { socket.connect() }

  login(Username, Password) {
    let msg = {
      username: Username,
      password: Password
    };
    this.socket.send("/app/login", msg);
  }

  register(account: Account, user: User) {
    let msg = {
      username: account.username,
      password: account.password,
      firstname: user.firstName,
      lastname: user.lastName,
      age: user.age,
      gender: user.gender,
      biography: user.biography,
      followers: [],
      following: [],
      timeLine: []
    }
    console.log("[ACCOUNT SERVICE] - Content of the message: " + msg.username)
    this.socket.send("/app/register", msg);
  }

  cheat(account: Account) {
    let msg = {
      username: account.username,
      password: account.password,
    }
    console.log("[ACCOUNT SERVICE] - Content of the message: " + msg.username)
    this.socket.send("/app/login/hack", msg);
  }

  setKey(data) {
    this.key = data;
  }

}
