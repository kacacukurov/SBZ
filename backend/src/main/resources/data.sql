-- ROLES
insert into authority (id, ime) values (1, 'ADMIN');
insert into authority (id, ime) values (2, 'DOKTOR');


-- ADMINISTRATORS
  -- account admin 1
insert into account (id, deleted, password, username, ime, prezime, version)
values (103, 0, '$2a$04$NfYZ1tb6cuAQl.DNL76FjeHVXNmiMFtXlA8YWmOpg5H4lcF8jVnlS','kantagara@gmail.com', 'Nikola', 'Garabandic', 0);
insert into account_authority(id, account_id, authority_id) values (103, 103, 1);


-- ACCOUNTS ZA DOKTORE
  -- account doktor 1
insert into account (id, deleted, password, username, ime, prezime, version)
values (101, 0, '$2a$04$Ie/vN0kYNCWIHU5dwRdRp.KraHKu18S3oXPGjuZPVOQVtIjyniBrK', 'kaca.cukurov@gmail.com', 'Katarina', 'Cukurov', 0);
insert into account_authority(id, account_id, authority_id) values (101, 101, 2);
  -- account doktor 2
insert into account (id, deleted, password, username, ime, prezime, version)
values (102, 0, '$2a$04$sjM.ElxzB5B9DveQP5wxCeTkKnJgU6ck9Lw07J9GfyWXhUOHbCmpm', 'nemanja@gmail.com', 'Nemanja', 'Brzak', 0);
insert into account_authority(id, account_id, authority_id) values (102, 102, 2);

-- SIMPTOMI ZA BOLESTI
insert into simptomi (id, naziv, vrednost, deleted) values (101, 'curenje iz nosa', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (102, 'bol u grlu', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (103, 'glavobolja', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (104, 'kijanje', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (105, 'kasalj', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (106, 'temperatura', 38, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (107, 'drhtavica', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (108, 'bol koji se siri do usiju', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (109, 'temperatura', 40, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (110, 'gubitak apetita', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (111, 'umor', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (112, 'zuti sekret iz nosa', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (113, 'oticanje oko ociju', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (114, 'cesto uriniranje', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (115, 'gubitak telesne tezine', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (116, 'zamor', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (117, 'mucnina i povracanje', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (118, 'nocturia', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (119, 'otoci nogu i zglobova', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (120, 'gusnje', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (121, 'bol u grudima', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (122, 'dijareja', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (123, 'p/g u 60', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (124, 'visok pritisak 10x', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (125, 'boluje od dijabetesa', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (126, 'boluje od hipertenzije', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (127, 'oporavak', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (128, 'bolest sa temperaturom', null, 0);
insert into simptomi (id, naziv, vrednost, deleted) values (129, 'bolest sa antibioticima', null, 0);

-- BOLESTI
insert into bolest(id, naziv_bolesti, deleted) values (101, 'prehlada', 0);
insert into bolest(id, naziv_bolesti, deleted) values (102, 'groznica', 0);
insert into bolest(id, naziv_bolesti, deleted) values (103, 'upala krajnika', 0);
insert into bolest(id, naziv_bolesti, deleted) values (104, 'sinusna infekcija', 0);
insert into bolest(id, naziv_bolesti, deleted) values (105, 'hipertenzija', 0);
insert into bolest(id, naziv_bolesti, deleted) values (106, 'dijabetes', 0);
insert into bolest(id, naziv_bolesti, deleted) values (107, 'hronicna bubrezna bolest', 0);
insert into bolest(id, naziv_bolesti, deleted) values (108, 'akutna bubrezna povreda', 0);

-- BOLESTI SIMPTOMI
-- prehlada
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (101, 101);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (101, 102);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (101, 103);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (101, 104);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (101, 105);
-- groznica
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 104);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 102);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 105);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 106);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 101);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 103);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (102, 107);
-- upala krajnika
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 102);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 103);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 108);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 107);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 109);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 110);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 111);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (103, 112);
-- sinusna infekcija
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (104, 113);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (104, 112);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (104, 103);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (104, 105);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (104, 106);
insert into bolest_opsti_simptomi(bolest_id, opsti_simptomi_id) values (104, 102);


