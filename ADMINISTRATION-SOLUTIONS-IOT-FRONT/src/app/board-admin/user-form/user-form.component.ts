import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/_models/user';
import { AdminService } from 'src/app/_services/admin.service';
import { first } from 'rxjs/operators';


@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  currentUser: any;
  u : any;

  users : User [];
  roles = ["ROLE_ADMIN","ROLE_USER","ROLE_SERVER"];
  isAddMode = true; 
  isupdated = false;
  userform: FormGroup;
  id: number;
  loading = true;
  submitted = false;
  selectedUser: User;
  errorMessage = '';
  

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute, 
    private router: Router, 
    private adminService: AdminService) 
  {}

  ngOnInit() {
    this.adminService.getAllUsers().subscribe(
      data => {
        console.log(data);
        this.users = data;
      },
      err => {
        this.users = JSON.parse(err.error).message;
      }
    );
    //this.id = this.route.snapshot.params['id'];
    this.initUserForm();
    
  }

  deleteUser(id:number){
    this.adminService.deleteUser(id).subscribe(
      data => {
        this.adminService.getAllUsers().subscribe(data=>{
        this.users = data;
      })
      },
      err => {
        this.users = JSON.parse(err.error).message;
      }
    );
  }
  updateU(id:number){
    this.isAddMode=false;
    this.u = this.adminService.getUser(id)
                .pipe(first())
                .subscribe(x => this.userform.patchValue(x));
  }
  addU(){
    this.initUserForm();
    this.isAddMode=true;
  }
  initUserForm(){
    this.userform = this.formBuilder.group({
      id: [0],
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      roles: [[]],      
    }, {
      //validator: MustMatch('password', 'confirmPassword')
    });
  }
  public onSelectAll() {
    const selected = this.roles.map(item => item);
    this.userform.get('roles').patchValue(selected);
  }

  public onClearAll() {
    this.userform.get('roles').patchValue([]);
  }

  get f() { return this.userform.controls; }
  onSubmit() {
    this.submitted = true;
    if (this.userform.invalid) {
        return;
    }
    this.loading = true;
    if (this.isAddMode) {
        this.createUser();
    } else {
        this.updateUser();
    }
  }

  private createUser() {
    this.adminService.saveUser(this.userform.value)
        .pipe(first())
        .subscribe({
          next: () => {
              this.loading = true;
              this.isupdated=false; 
              this.adminService.getAllUsers().subscribe(data=>{
                this.users = data;
              })
              this.router.navigate(['/admin'], { relativeTo: this.route });
          },
          error: error => {
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }

  private updateUser() {
    this.adminService.updateUser(this.id, this.userform.value)
      .pipe(first())
      .subscribe({
          next: () => {
              //this.alertService.success('User updated', { keepAfterRouteChange: true });
              this.router.navigate(['/all'], { relativeTo: this.route });
          },
          error: error => {
              //this.alertService.error(error);
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }
  changeisUpdate(){
    this.initUserForm();
    this.isupdated=false;  
  }
}

