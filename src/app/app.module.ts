import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSliderModule} from '@angular/material/slider';
import {MatRadioModule} from '@angular/material/radio';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';
import { HttpModule } from '@angular/http';
import {MatStepperModule} from '@angular/material/stepper';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';








import { AppComponent } from './app.component';
import 'hammerjs';
import { LoginComponent } from './login/login.component';
import { HerosComponent } from './heros/heros.component';

import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HerosComponent,
   
    UserComponent
  ],
  imports: [
    BrowserModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatSliderModule,
    MatRadioModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    MatSidenavModule,
    MatGridListModule,
    HttpModule,
    MatStepperModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatTabsModule

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
