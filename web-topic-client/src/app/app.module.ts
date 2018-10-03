import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';
import { TopicFormComponent } from './components/topic-form/topic-form.component';

@NgModule({
  declarations: [
    AppComponent,
    TopicListComponent,
    TopicFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
