delete from slike;
delete from proizvod;
delete from authority;
delete from user_authority;
delete from korisnik;

insert into korisnik(Id, Ime, Prezime, Username, Password, Kontakt, Lokacija, Email, BrojTelefona)
    values
        (1,'Ime','Prezime', 'admin','$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy','0993698595','Lokacija','Mail','09993698595'),
        (2,'Ime','Prezime', 'user','$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy','0993698595','Lokacija','Mail','09993698595');

insert into proizvod(Id, Korisnik_Id, Naslov, Opis, Stanje, Cijena, Kategorija)
    values
        (1,1,'Predmet','Opis','Stanje',25,'Kategorija');

insert into slike (Id, Proizvod_Id, Proizvod_Korisnik_Id, Slika)
    values
        (1,1,1,'54455357'),
        (2,1,1,'54455358'),
        (3,1,1,'54455355');


insert into authority (id, name)
values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into user_authority (user_id, authority_id)
values
(1, 1),
(2, 2);