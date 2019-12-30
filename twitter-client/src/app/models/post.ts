export class Post {
    postID: number;
    userID: number;
    name: string;
    content: string;
    date: string;
    postKind: postKind;
}

export enum postKind {
    TWEET, RETWEET, COMMENT, FOLLOW
}