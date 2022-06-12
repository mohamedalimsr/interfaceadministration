import { Component, OnInit } from '@angular/core';
import { WebClientAccount } from '../_models/UserAccount';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  webClientAccount: WebClientAccount;
  public doughnutChartLabels = ['Account Fee Per Day', 'Device Fee Per Day', 'Device Fee Per Month', 'Sim Card Fee Per Month'];
  public doughnutChartData = [80, 83, 94, 87];
  public doughnutChartType = 'doughnut';

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels = ['Account Fee Per Day', 'Device Fee Per Day', 'Device Fee Per Month', 'Sim Card Fee Per Month'];  
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    {data: [80, 83, 94, 87], label: 'Fees statistcs'},
  ];

  constructor(private userService: UserService, private token: TokenStorageService) { }

  ngOnInit() {
    this.userService.getWebAccountById(this.token.getUser().id).subscribe(
      data => {
        this.webClientAccount = data;
      },
      err => {
        this.webClientAccount = JSON.parse(err.error).message;
      }
    );

  }
  private getWebAccountStat(){
    
    this.doughnutChartData.push(this.webClientAccount.accountFeeByMonth);
    this.doughnutChartData.push(this.webClientAccount.deviceFeeByDay);
    this.doughnutChartData.push(this.webClientAccount.deviceFeePerMonth);
    this.doughnutChartData.push(this.webClientAccount.simCardFeePerMonth);
  }
}
