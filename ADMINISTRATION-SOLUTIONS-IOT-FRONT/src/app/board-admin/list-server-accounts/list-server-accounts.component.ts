import { formatDate } from '@angular/common';
import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Role } from 'src/app/_models/role';
import { ServerClientAccount } from 'src/app/_models/UserAccount';
import { AdminService } from 'src/app/_services/admin.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-list-server-accounts',
  templateUrl: './list-server-accounts.component.html',
  styleUrls: ['./list-server-accounts.component.css']
})
export class ListServerAccountsComponent implements OnInit {

  isAddMode: Boolean = true;
  isDetailMode: Boolean = false;
  submitted = false;
  loading = true;
  errorMessage = '';
  u: any;
  totalElements: number = 0;

  @ViewChild('model', { static: false}) model: ElementRef;

  serverAccountForm: FormGroup;
  serverClientAccounts : ServerClientAccount [];

  constructor(private formBuilder: FormBuilder,
            private route: ActivatedRoute, 
            private router: Router,
            private adminService: AdminService,
            private token: TokenStorageService) { }

  ngOnInit() {
    this.getServerAccounts({page:"0", size:"5"});
    this.initUserForm();
  }
  private getServerAccounts(request){
    this.adminService.getAllServerAccounts(request).subscribe(
      data => {
        this.serverClientAccounts = data['content'];
        this.totalElements = data['totalElements'];
      },
      err => {
        this.serverClientAccounts = JSON.parse(err.error).message;
      }
    );
  }
  nextPage(event: PageEvent) {
    const request = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getServerAccounts(request);
  }
  initUserForm(){
    this.serverAccountForm = this.formBuilder.group({
      idServerClientAccount: [0],
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      pseudo: [''],
      ipAdresse: [''],
      intervaleStart: [0],
      intervaleEnd: [0],
      dateCreation: [], 
      dateExpiration: [new Date()],
      administratorCompte: [this.token.getUser()],
      roles: [[]],      
    }, {
      //validator: MustMatch('password', 'confirmPassword')
    });
  }
  onSubmit() {
    this.submitted = true;
    if (this.serverAccountForm.invalid) {
        return;
    }
    this.loading = true;
    if (this.isAddMode) {
      this.createServerAccount()
    } else {
      this.updateServerAccount();
    }
  }
  private createServerAccount() {
    let serverAccount = this.serverAccountForm.value;
    let role: Role = new Role("ROLE_SERVER");
    serverAccount.administratorCompte = this.token.getUser();
    serverAccount.role = role;
    this.adminService.addServerAccount(serverAccount)
        .pipe(first())
        .subscribe({
          next: () => {
              this.loading = true;
              this.getServerAccounts({page:"0", size:"5"});
              this.changeisUpdate();
              this.router.navigate(['/listServerAccounts']);
          },
          error: error => {
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }
  private updateServerAccount() {
    let serverAccount = this.serverAccountForm.value;
    let id = this.serverAccountForm.value.idServerClientAccount;
    serverAccount.administratorCompte = this.token.getUser();
    this.adminService.updateServerAccount(id,serverAccount)
        .pipe(first())
        .subscribe({
          next: () => {
              this.loading = true;
              this.getServerAccounts({page:"0", size:"5"});
              this.changeisUpdate();
              this.router.navigate(['/listServerAccounts']);
          },
          error: error => {
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }


  addServerAccountForm(){
    this.initUserForm();
    this.isAddMode=true;
  }
  updateServerAccountForm(id:number){
    console.log(id);
    this.isAddMode=false;
    this.u = this.adminService.getServerAccount(id)
                .pipe(first())
                .subscribe(
                  x => {
                    this.serverAccountForm.patchValue(x);
                    this.serverAccountForm.patchValue({
                      dateCreation: formatDate(x.dateCreation, 'yyyy-MM-dd', 'en'),
                      dateExpiration: formatDate(x.dateExpiration, 'yyyy-MM-dd', 'en'),
                    });
                  }
                );
    console.log(this.u);
  }

  
  detailsServerClientAccount(id: number){
    console.log(id);
    this.isDetailMode=true;
    this.u = this.adminService.getServerAccount(id)
                .pipe(first())
                .subscribe(
                  x => {
                    this.serverAccountForm.patchValue(x);
                    this.serverAccountForm.patchValue({
                      dateCreation: formatDate(x.dateCreation, 'yyyy-MM-dd', 'en'),
                      dateExpiration: formatDate(x.dateExpiration, 'yyyy-MM-dd', 'en'),
                    });
                  }
                );
  }
  deleteServerClientAccount(id: number){
    this.adminService.deleteServerAccount(id).subscribe(
      data => {
        this.getServerAccounts({page:"0", size:"5"});
        this.changeisUpdate();
        this.router.navigate(['/listServerAccounts']);
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
  updateAttributes(){
    
  }

  @HostListener('document:click',['$event'])
  clickOutsideModelDialog(event){
    if (this.model.nativeElement.contains(event.target)){
      this.isDetailMode = false;
    }
  }

}
