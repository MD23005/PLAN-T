INSERT INTO tutores (email,nombre,password,rol)
VALUES
  ('phasellus.at.augue@google.com','Rahim Finley','T5D2KQNC1HOT7QN','tutor'),
  ('commodo@google.com','Justine Mccarthy','X1A6ISBM1BWX2GU', 'tutor'),
  ('auctor.nunc.nulla@protonmail.com','Shay Park','M8O6DICA9TWW1TE', 'tutor'),
  ('nibh.sit@yahoo.com','Barrett Benson','R6N6UCVG4NFZ7MK', 'tutor'),
  ('aenean.egestas@protonmail.com','Tarik Arnold','L2T2MDHL8NRV4SA', 'tutor'),
  ('elementum.lorem.ut@yahoo.com','Raymond Mclean','V6V9OQFR7HOQ8VX', 'tutor'),
  ('convallis.ante.lectus@hotmail.com','Kiona Higgins','H6I7KMTN7ZYE6JP', 'tutor'),
  ('interdum.feugiat.sed@icloud.com','Deacon Fox','T6K8AQEG7DNN3ZL', 'tutor'),
  ('ipsum.cursus@yahoo.com','Cullen Castaneda','T8T7HJGX8KBJ6IM', 'tutor'),
  ('libero.at@google.com','Cheryl Alvarado','N7Z3MYLQ5EIO6SI', 'tutor');

INSERT INTO admin (email,nombre,password,rol)
VALUES
  ('orci.sem@hotmail.com','Yen Parrish','X5I9VEKI5QJX9YB','admin'),
  ('molestie.in@aol.com','Larissa Delgado','O5X6UVLH1GIL7JD','admin');

INSERT INTO public.grupos (tutor_id,nombre) VALUES
	 (1,'Grupo A'),
	 (1,'Grupo B'),
	 (2,'Grupo C');
