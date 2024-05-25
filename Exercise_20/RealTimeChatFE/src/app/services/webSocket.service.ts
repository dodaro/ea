import * as Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatMessage } from '../model/chatMessage';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  [x: string]: any;

  private stompClient: any;

  constructor() {
    this.initConnectionSocket();
   }

  initConnectionSocket()
  {
    const socketUrl='//localhost:8081/kittify-socket';
    const socket = new SockJS(socketUrl);
    this.stompClient = Stomp.over(socket);
  }

  joinRoom(roomId: string, callback: (message: string) => void)
  {
    const userId = this.getUserIdFromUrl();
    const { chatComponent } = this;
    this.stompClient.connect({}, () => {
      this.stompClient.subscribe('/topic/' + roomId, (message: any) => {
        const messageBody = JSON.parse(message.body);
        if(userId!=messageBody.user)
        {
          callback(messageBody.message);
        }
      });
    });
  }
  

  sendMessage(roomId: string, chatMessage: ChatMessage)
  {
    this.stompClient.send('/app/chat/' + roomId, {}, "{\"message\": \"" + chatMessage.message + "\", \"user\": \"" + chatMessage.user + "\"}");
  }


  public disconnect(): void {
    if (this.stompClient) {
      this.stompClient.disconnect( () => {});
    }
  }

  getUserIdFromUrl() {
    const currentUrl = window.location.href;
  
    const urlParts = currentUrl.split('/');
    const userId = urlParts[urlParts.length - 1];
  
    return userId;
  }
}