import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SocketService } from 'src/app/services/socket.service';
import { User } from 'src/app/models/user';
import { Client } from 'webstomp-client';
import { PostService } from 'src/app/services/post.service';
import { Post, postKind } from 'src/app/models/post';

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
  private postContent: string = "";
  private message: string = "";
  private searchElement: string = "";
  private posts: Post[] = [];
  private timeline = [];

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
    private postService: PostService,
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
  createPost() {
    this.postService.post(this.user.id, this.postContent);
  }

  initPost(dto): Post {
    let post: Post;
    switch (dto.postKind) {
      case postKind.TWEET:
        return dto;
      case postKind.RETWEET:
        post = {
          postID: dto.postID,
          userID: dto.userID,
          name: dto.name,
          content: "user: " + dto.oldName + " tweeted: " + dto.oldContent +
            " RT content: " + dto.content,
          date: dto.date,
          postKind: postKind.RETWEET
        }
        return post;
      case postKind.COMMENT:
        return;
      case postKind.FOLLOW:
        return;
    }
  }

  initAllSubscribtions() {
    //Chat requests
    this.client.subscribe(`/topic/user/chatRequest/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      this.chatKey = dto.chatID
      this.subToChat();
    })

    //Follow requests
    this.client.subscribe(`/topic/user/request/follow/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      alert("You got a follow request from " + dto.username);
      console.log("you got a request!")
      this.service.sendAnswer(this.user.id, dto.userID, true);
    })

    //Answer of followrequest *TODO: Give player a choice before sending awnser back
    this.client.subscribe(`/topic/user/${this.user.id}/followRequestAnswer`, callback => {
      let dto = JSON.parse(callback.body);
      console.log("yeeeeah you got friends now!")
      alert("You and " + dto.username + " are now friends");
    })

    //Own activity as in: tweeting/retweeting/commenting and following other users
    this.client.subscribe(`/topic/post/activity/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);

      this.posts.push(dto);
    })

    //Timeline : this contains the activity of other players by showing them in order
    //TODO: create a subscribemapping that gets all of the recent activity of the user u are following, when you enter this page
    this.client.subscribe(`/topic/post/timeline/${this.user.id}`, callback => {
      let dto = JSON.parse(callback.body);
      //TODO: check welke soort activiteit het is voordat ik de info laat zien
      this.timeline.push(dto.content);
    })
  }
}
