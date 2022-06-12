import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageEvent } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Boitier } from 'src/app/_models/boitier';
import { ServerService } from 'src/app/_services/server.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-etat-boitiers',
  templateUrl: './etat-boitiers.component.html',
  styleUrls: ['./etat-boitiers.component.css']
})
export class EtatBoitiersComponent implements OnInit {

  @ViewChild('model', { static: false}) model: ElementRef;

  totalElements: number = 0;
  listBoitiers: Boitier[];
  boitierForm: FormGroup;
  errorMessage = '';
  loading = true;
  isAddMode: Boolean = true;


  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute, 
    private router: Router,
    private serverService: ServerService,
    private token: TokenStorageService) { }

  ngOnInit() {
    this.getAllBoitiers({page:"0", size:"5"});
    this.initBoitierForm();
  }
  initBoitierForm(){
    this.boitierForm = this.formBuilder.group({
      idBoitier: [0],
      label: ['', Validators.required],
      etatBoitier: ['', Validators.required],
      streamId: [],
      rawstream_id: [],
      matricule: ['', Validators.required],
      etat: ['', Validators.required],
      latitude: [], 
      longitude: [],
      validite: [''], 
      vitesse: [''],
      ignition: [''],
      gps: [''],
      gsm: [''],
      dateGps: [],
      dateServeur: [],
      idTrajet: [],
      dateOption: [],
      hlr: [''],
      imei: [''],
      version: ['']
    }, {
      //validator: MustMatch('password', 'confirmPassword')
    });
  }
  nextPage(event: PageEvent) {
    const request = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getAllBoitiers(request);
  }
  initiateModelDialog(){
    this.initBoitierForm();
  }
  private getAllBoitiers(request){
    this.serverService.getAllBoitiers(request).subscribe(
      data => {
        this.listBoitiers = data['content'];
        this.totalElements = data['totalElements'];
      },
      err => {
        this.listBoitiers = JSON.parse(err.error).message;
      }
    );
  }
  onSubmit(){
    this.createBoitier();
  }

  private createBoitier(){
    let boitier = this.boitierForm.value;
    this.serverService.addBoitier(boitier)
        .pipe(first())
        .subscribe({
          next: () => {
              this.getAllBoitiers({page:"0", size:"5"});
              this.changeisUpdate();
              this.router.navigate(['/adminDash']);
          },
          error: error => {
              this.errorMessage = error.error.message;
              this.loading = false;
          }
      });
  }

  changeisUpdate(){
    this.initBoitierForm();
    this.isAddMode = false;
  }

  @HostListener('document:click',['$event'])
  clickOutsideModelDialog(event){
    if (this.model.nativeElement.contains(event.target)){
      if(this.isAddMode == true){
        this.changeisUpdate();
      }
    }
  }

}
