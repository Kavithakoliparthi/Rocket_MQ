import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';


@Component({
  selector: 'app-heros',
  templateUrl: './heros.component.html',


 styleUrls: ['./heros.component.css']
})


export class HerosComponent implements OnInit {

  constructor(private http:Http) { }

  ngOnInit() {
  }
  panelOpenState: boolean = false;


  step = 0;

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  

 
  
}
