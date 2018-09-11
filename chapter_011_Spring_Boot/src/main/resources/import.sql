Insert into users (id,createdate,name,login,email,password) Values(3,'2018-08-31 16:41:39.852000','User','User','user@gmail.com','vfitymrf');

Insert into transmission (name) Values('КПП-6');
Insert into transmission (name) Values('AКПП');
Insert into transmission (name) Values('Robotic');
Insert into transmission (name) Values('КПП-4');

Insert into Engine (name) Values('V6');
Insert into Engine (name) Values('V5');
Insert into Engine (name) Values('V3');

Insert into GearBox (name) Values('BOX1');
Insert into GearBox (name) Values('BOX2');
Insert into GearBox (name) Values('BOX3');
Insert into GearBox (name) Values('BOX4');
Insert into GearBox (name) Values('BOX5');

Insert into Car (name,id_transmission,id_engine,id_gearbox) Values('Жига 6',1,1,1);
Insert into Car (name,id_transmission,id_engine,id_gearbox) Values('Ford Mustang',2,2,2);
Insert into Car (name,id_transmission,id_engine,id_gearbox) Values('VW Golf',3,3,5);