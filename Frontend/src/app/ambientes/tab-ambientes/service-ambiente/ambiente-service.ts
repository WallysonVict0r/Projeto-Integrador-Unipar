import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { AuthService } from '../../../auth/auth.service';
import { Ambiente } from './ambiente';

@Injectable({
  providedIn: 'root'
})

export class AmbienteService {

  private apiUrl='http://assetinsight.awahosting.cloud:8080/ambiente/all';

  constructor(private http:HttpClient, private auth: AuthService){}

  getAmbientes(): Observable<Ambiente[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });

    console.log(headers);
    return this.http.get<Ambiente[]>(this.apiUrl, { headers });
  }
}
