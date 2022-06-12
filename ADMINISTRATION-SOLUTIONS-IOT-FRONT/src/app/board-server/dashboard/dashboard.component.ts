import { Component, OnInit } from '@angular/core';
import { WebClientAccount } from 'src/app/_models/UserAccount';
import { ServerService } from 'src/app/_services/server.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  webClientAccounts: WebClientAccount [];
  totalElements: number = 0;
  constructor(private serverService: ServerService) { }
  
  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels = [];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [{data:[], label:''}];

  ngOnInit() {
    this.getWebAccounts({page:"0", size:"100"});
    console.log(this.webClientAccounts);
  }
  private getWebAccounts(request){
    this.serverService.getAllWebAccounts(request).subscribe(
      data => {
        this.webClientAccounts = data['content'];
        console.log(this.webClientAccounts);
        this.totalElements = data['totalElements'];
      },
      err => {
        this.webClientAccounts = JSON.parse(err.error).message;
      }
    );
  }

  public showAccountFeesPerDay(){
    this.barChartData.pop();
    this.barChartLabels = [];
    var listDeviceFeeByDay: number[] = [];
    this.webClientAccounts.forEach(webAccount=>{
      this.barChartLabels.push(webAccount.username);
      listDeviceFeeByDay.push(webAccount.accountFeeByMonth);
    });
    let dict = {data:listDeviceFeeByDay, label:'Account Fee Per Day'}
    this.barChartData.push(dict);
  }
  public showDeviceFeesPerDay(){
    this.barChartData.pop();
    this.barChartLabels = [];
    var listDeviceFeeByDay: number[] = [];
    this.webClientAccounts.forEach(webAccount=>{
      this.barChartLabels.push(webAccount.username);
      listDeviceFeeByDay.push(webAccount.deviceFeeByDay);
    });
    let dict = {data:listDeviceFeeByDay, label:'Device Fee Per Day'}
    this.barChartData.push(dict);
  }
  public showDeviceFeesPerMonth(){
    this.barChartData.pop();
    this.barChartLabels = [];
    var listDeviceFeeByDay: number[] = [];
    this.webClientAccounts.forEach(webAccount=>{
      this.barChartLabels.push(webAccount.username);
      listDeviceFeeByDay.push(webAccount.deviceFeePerMonth);
    });
    let dict = {data:listDeviceFeeByDay, label:'Device Fee Per Month'}
    this.barChartData.push(dict);
  }
  public showSimCardFeesPerMonth(){
    this.barChartData.pop();
    this.barChartLabels = [];
    var listDeviceFeeByDay: number[] = [];
    this.webClientAccounts.forEach(webAccount=>{
      this.barChartLabels.push(webAccount.username);
      listDeviceFeeByDay.push(webAccount.simCardFeePerMonth);
    });
    let dict = {data:listDeviceFeeByDay, label:'Sim Card Fee Per Month'}
    this.barChartData.push(dict);
  }



}
