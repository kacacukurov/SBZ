import {noviPacijentDTO} from "./noviPacijentDTO";
import {dijagnozaDTO} from "./dijagnozaDTO";

export class pacijent{
  pacijentDTO: noviPacijentDTO;
  dijagnoze: Array<dijagnozaDTO>;
  lekovi_alergija: Array<string>;
  sastojci_alergija: Array<string>;
}
