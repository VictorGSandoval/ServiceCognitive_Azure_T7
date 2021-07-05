import { Component } from '@angular/core';
import { TextService } from './services/text-service';
import { Comentario, Texto, Lenguaje, PalabrasClaves, Entidad, EntidadResponse } from './models/text.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  constructor(private textService: TextService) {}

  input: string;
  inputLenguaje: string;
  inputPalabra: string;
  inputEntidad: string;
  texto: Texto = new Texto;
  listaResponse: Comentario[] = [];
  listaLenguajes: Lenguaje[] = [];
  response: Comentario;
  lenguaje: Lenguaje;
  palabraClave: PalabrasClaves;
  entidad: EntidadResponse;

  analizarSentimiento() {
    this.response = new Comentario;
    this.texto.texto = this.input;
    this.textService.analizarSentimiento(this.texto).subscribe((data) => {
      if(data.Tipo === 'positive') {
        this.response.emoji = '😄';
      }
      if(data.Tipo === 'neutral') {
        this.response.emoji = '😐';
      }
      if(data.Tipo === 'negative') {
        this.response.emoji = '😭';
      }
      this.response.texto = data.Texto;
      this.listaResponse.unshift(this.response);
    });
    this.input = '';
  }

  detectarLeguaje() {
    this.lenguaje = new Lenguaje;
    this.texto.texto = this.inputLenguaje;
    this.textService.detectarIdioma(this.texto).subscribe(data => {
      this.lenguaje.texto = data.Texto;
      this.lenguaje.lenguaje = data.Lenguaje;
      this.listaLenguajes.unshift(this.lenguaje);
    });
    this.inputLenguaje = '';
  }

  reconocerPalabras() {
    this.palabraClave = new PalabrasClaves();
    this.texto.texto = this.inputPalabra;
    this.textService.detectarPalabrasClaves(this.texto).subscribe(data => {
      this.palabraClave.texto = data.Texto;
      this.palabraClave.palabras = data.Palabras;
    });
    this.inputPalabra = '';
  }

  reconocerEntidades() {
    this.entidad = new EntidadResponse();
    this.texto.texto = this.inputEntidad;
    this.textService.reconocerEntidades(this.texto).subscribe(data => {
      this.entidad.text = data.Texto;
      this.entidad.entidades = data.Entidades;
    });
    this.inputEntidad = '';
  }
  
}