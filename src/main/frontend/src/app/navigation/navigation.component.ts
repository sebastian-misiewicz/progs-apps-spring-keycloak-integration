import {Component} from '@angular/core';
import {UserService} from "../service/user.service";
import {IUser} from "../interface/iuser";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  currentUser!: IUser;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
    });
  }

}
