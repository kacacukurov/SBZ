export class Token {
  sub: string;
  created: Date;
  roles: Array<string>;
  apartmentID: number;
  buildingID: number;
  id: number;
  exp: Date;
}
