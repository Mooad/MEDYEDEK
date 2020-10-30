import { Component, OnInit } from '@angular/core';
import { ErrorDialogService } from '../services/errordialog.service';

@Component({
  selector: 'app-errordialog',
  templateUrl: './errordialog.component.html',
  styleUrls: ['./errordialog.component.scss']
})
export class ErrordialogComponent implements OnInit {
  public ERRORPAGE = 'erropage.jpg';

  constructor(private errorDialogService: ErrorDialogService) { }

  ngOnInit(): void {
  }

}
