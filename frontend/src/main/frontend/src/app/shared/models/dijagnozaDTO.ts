import {simptomDTO} from "./simptomDTO";

export class dijagnozaDTO{
  naziv_bolesti: string;
  jmbg: string;
  simptomi: Array<simptomDTO>
  datum: string;
  id: number;
}
