import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../services/web-socket.service';

@Component({
  selector: 'app-web-socket-component',
  templateUrl: './web-socket-component.component.html',
  styleUrls: ['./web-socket-component.component.scss']
})
export class WebSocketComponent implements OnInit {
  messages: string[] = [];
  newMessage: string = '';

  constructor(private webSocketService: WebSocketService) { }

  ngOnInit(): void {
    this.webSocketService.messages.subscribe(message => {
      this.messages.push(message);
    });
  }

  sendMessage(): void {
    this.webSocketService.sendMessage(this.newMessage);
    this.newMessage = ''; // Clear the input after sending
  }
}
