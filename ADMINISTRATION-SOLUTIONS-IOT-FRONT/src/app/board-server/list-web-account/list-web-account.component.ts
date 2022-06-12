import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Role } from 'src/app/_models/role';
import { WebClientAccount } from 'src/app/_models/UserAccount';
import { ServerService } from 'src/app/_services/server.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-list-web-account',
  templateUrl: './list-web-account.component.html',
  styleUrls: ['./list-web-account.component.css']
})
export class ListWebAccountComponent implements OnInit {

  isAddMode: Boolean = true;
  isDetailMode: Boolean = false;
  submitted = false;
  loading = true;
  errorMessage = '';
  u: any;

  @ViewChild('model', { static: false}) model: ElementRef;

  webAccountForm: FormGroup;
  webClientAccounts: WebClientAccount [];
  totalElements: number = 0;


  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute, 
    private router: Router,
    private serverService: ServerService,
    private token: TokenStorageService) { }

  ngOnInit() {
    this.getWebAccounts({page:"0", size:"5"});
    this.initUserForm();
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
  initUserForm(){
    this.webAccountForm = this.formBuilder.group({
      idCompteClientWeb: [0],
      login: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      dateCreation: [], 
      dateExpiration: [],
      role: ['ROLE_WEB'], 
      rawPassword: [''],
      code_pays: [''],
      pool: [],
      telephone: [],
      area: [''],
      compteClientServer: [this.token.getUser()],
      notificationSubquery: [''],
      mobileNotif: [false],
      deviceFeeByDay: [],
      accountFeeByMonth: [],
      deviceFeePerMonth: [],
      simCardFeePerMonth: [],
      expired: [false],
    }, {
      //validator: MustMatch('password', 'confirmPassword')
    });
  }

  onSubmit() {
    console.log(this.webAccountForm.value);
    this.submitted = true;
    this.loading = true;
    if (this.isAddMode) {
      this.createWebAccount()
    } else {
      this.updateWebAccount();
    }
  }

  private createWebAccount(){
    let webAccount = this.webAccountForm.value;
    let role: Role = new Role("ROLE_WEB");
    webAccount.compteClientServer = this.token.getUser();
    webAccount.role = role;
    this.serverService.addWebAccount(webAccount)
        .pipe(first())
        .subscribe({
          next: () => {
              this.loading = true;
              this.getWebAccounts({page:"0", size:"5"});
              this.changeisUpdate();
              this.router.navigate(['/server/webAccounts']);
          },
          error: error => {
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }
  private updateWebAccount(){
    let webAccount = this.webAccountForm.value;
    console.log(webAccount);
    let id = this.webAccountForm.value.idWebClientAccount;
    webAccount.administratorCompte = this.token.getUser();
    this.serverService.updateWebAccount(id,webAccount)
        .pipe(first())
        .subscribe({
          next: () => {
              this.loading = true;
              this.getWebAccounts({page:"0", size:"5"});
              this.changeisUpdate();
              this.router.navigate(['/server/webAccounts']);
          },
          error: error => {
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }

  detailsWebClientAccount(id: number){
  
    this.isDetailMode=true;
    this.u = this.serverService.getWebAccount(id)
                .pipe(first())
                .subscribe(
                  x => {
                    console.log(x);
                    this.webAccountForm.patchValue(x);
                  }
                );
  }
  updateWebAccountForm(id: number){
    this.isAddMode=false;
    this.u = this.serverService.getWebAccount(id)
                .pipe(first())
                .subscribe(
                  x => {
                    this.webAccountForm.patchValue(x);
                  }
                );
  }
  deleteWebClientAccount(id: number){
    this.serverService.deleteWebAccount(id).subscribe(
      data => {
        this.getWebAccounts({page:"0", size:"5"});
        this.changeisUpdate();
        this.router.navigate(['/server/listWebAccounts']);
      },
      err => {
        console.log(JSON.parse(err.error).message);
      }
    );
  }

  changeisUpdate(){
    this.initUserForm();
    this.isAddMode=true;  
    this.isDetailMode = false;
  }

  @HostListener('document:click',['$event'])
  clickOutsideModelDialog(event){
    if (this.model.nativeElement.contains(event.target)){
      this.isDetailMode = false;
    }
  }

}
