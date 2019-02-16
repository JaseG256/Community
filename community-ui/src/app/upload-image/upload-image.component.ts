import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {

  selectedFile: File = null;

  constructor(private http: HttpClient) { }

  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  OnUpload() {
    const formData = new FormData();
    formData.append('image', this.selectedFile, this.selectedFile.name);
    this.http.post('http://localhost:8080/api/uploadFile', this.selectedFile)
      .subscribe(res => {
        console.log(res);
      });
  }

  ngOnInit() {
  }

}