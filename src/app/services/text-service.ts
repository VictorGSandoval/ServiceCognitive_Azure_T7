import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Texto, Comentario } from '../models/text.model';

@Injectable({
    providedIn: 'root'
  })
  
  export class TextService {

    url = environment.api;

    constructor(private http: HttpClient) { }

    public analizarSentimiento(string: Texto) {
      return this.http.post<any>(`${this.url}/sentimiento`, string);
    }

    public detectarIdioma(string: Texto) {
      return this.http.post<any>(`${this.url}/detectar-lenguaje`, string);
    }

    public reconocerEntidades(string: Texto) {
      return this.http.post<any>(`${this.url}/reconocer-entidades`, string);
    }

    public detectarPalabrasClaves(string: Texto) {
      return this.http.post<any>(`${this.url}/palabras-claves`, string);
    }

  }