CREATE TABLE "categoriaFuncao" (
  "idCategoria" SERIAL PRIMARY KEY,
  "nomeCategoria" text NOT NULL UNIQUE,
  "salarioCategoria" numeric (5,2),
  "descricaoCategoria" text
);

CREATE TABLE "localTrabalho" (
  "idLocalTrabalho" SERIAL PRIMARY KEY,
  "nomeLocalTrabalho" text NOT NULL UNIQUE,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp
);

CREATE TABLE "usuario" (
  "idUsuario" SERIAL PRIMARY KEY,
  "nomeUsuario" text NOT NULL UNIQUE,
  "senha" text NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp
);

CREATE TABLE "colaborador" (
  "idColaborador" SERIAL PRIMARY KEY,
  "nomeColaborador" text NOT NULL,
  "dataNascimento" timestamp NOT NULL,
  "rg" text NOT NULL,
  "cpf" text NOT NULL,
  "email" text,
  "cargaHoraria" time,
  "tipoMoradia" text,
  "cep" text,
  "rua" text,
  "numero" text,
  "complemento" text,
  "bairro" text,
  "cidade" text,
  "estado" text,
  "id_funcao" int,
  "ativo" int,
  "created_at" timestamp,
  "updated_at" timestamp
);

ALTER TABLE "colaborador" ADD FOREIGN KEY ("id_funcao") REFERENCES "categoriaFuncao" ("idCategoria");

-- create procedure
CREATE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql

-- auto updated_at user
CREATE TRIGGER set_timestamp 
BEFORE UPDATE ON usuario
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- auto updated_at localTrabalho
CREATE TRIGGER set_timestamp_localTrabalho 
BEFORE UPDATE ON localtrabalho
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();


